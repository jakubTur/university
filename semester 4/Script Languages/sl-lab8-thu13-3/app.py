import re
import os
import sys
import logging
import ipaddress


# 1
def read_config_file(filepath='lab.config'):

    if not os.path.exists(filepath):
        print("Config file not found. Exiting.")
        sys.exit(1)
    LEVELS = {
        'CRITICAL': logging.CRITICAL,
        'ERROR': logging.ERROR,
        'WARNING': logging.WARNING,
        'INFO': logging.INFO,
        'DEBUG': logging.DEBUG,
        'NOTSET': logging.NOTSET
    }
    config = {
        'Display': {},
        'LogFile': {},
        'Config': {}
    }
    current_section = None

    with open(filepath) as file:
        for line in file:
            line = line.strip()
            if not line or line.startswith('#'):
                continue
            header_match = re.match(r"\[(.+)\]", line)
            if header_match:
                current_section = header_match.group(1)
            else:
                param_match = re.match(r"([^=]+)=(.+)", line)
                if param_match and current_section:
                    key = param_match.group(1).strip()
                    value = param_match.group(2).strip()
                    if current_section == 'Display':
                        config['Display'][key] = value
                    elif current_section == 'LogFile':
                        config['LogFile'][key] = value
                    elif current_section == 'Config':
                        if key == 'debug':
                            config['Config'][key] = LEVELS.get(value, logging.INFO)
                        else:
                            config['Config'][key] = value

    if 'name' not in config['LogFile']:
        config['LogFile']['name'] = "default.log"
    if 'lines_per_page' not in config['Display']:
        config['Display']['lines_per_page'] = '10'
    if 'debug' not in config['Config']:
        config['Config']['debug'] = logging.INFO
    return config


# 2
def read_log_file(filename):
    if not os.path.exists(filename):
        print(f"Log file '{filename}' not found. Exiting.")
        sys.exit(1)
    with open(filename) as file:
        return file.readlines()


# 3
def parse_log_line(line):
    regex = re.compile(r'(\d+\.\d+\.\d+\.\d+)\s+- -\s+\[(.*?)\]\s+"(.*?)"\s+(\d{3})\s+(\d+|-)\s+"(.*?)"\s+"(.*?)"')
    match = regex.match(line)
    if match:
        ip, timestamp, request, status, size, ref, ug = match.groups()
        return {
            'ip': ip,
            'timestamp': timestamp,
            'request': request,
            'status': int(status),
            'size': int(size) if size.isdigit() else 0,
            'referer': ref,
            'userAgent': ug
        }
    return None


# 4
def analyze_log_lines(lines):
    entries = []
    for line in lines:
        entry = parse_log_line(line)
        if entry:
            entries.append(entry)
    return entries


# 5
def ip_in_subnet(ip, subnet, mask_length):
    network = ipaddress.IPv4Network(f"{subnet}/{mask_length}", strict=False)
    return ipaddress.IPv4Address(ip) in network


hardcoded_subnet = "101.200.0.0"
student_index = 280292
mask_length = student_index % 16 + 8


def print_requests_from_subnet(entries, subnet=hardcoded_subnet, mask_len=mask_length):
    count = 0
    per_page = int(configuration['Display']['lines_per_page'])
    for entry in entries:
        if ip_in_subnet(entry['ip'], subnet, mask_len):
            print(entry)
            count += 1
            if count % per_page == 0:
                user_input = input("Press Enter to show more lines")
                if user_input != "":
                    break


# 6
def print_browser_requests(entries, browser_name='Chrome'):
    for entry in entries:
        if browser_name.lower() in entry['userAgent'].lower():
            print(entry)


# 7
def printBytes(entries):
    separator = configuration['Display']['separator']
    for entry in entries:
        match = re.match(r'([A-Z]+)\s', entry['request'])
        if match:
            method = match.group(1)
            if (method == configuration['Display']['filter']):
                print(f"\n{method}{separator}{entry['size']}")


def run():
    print("This is the lab assignment solution")
    global configuration
    configuration = read_config_file()
    logging.basicConfig(level=configuration['Config']['debug'])
    requests = read_log_file('access_log-20201025.txt')
    objects = []
    for i in requests:
        objects.append(parse_log_line(i))
    print_requests_from_subnet(objects)
    print_browser_requests(objects)
    printBytes(objects)


if __name__ == "__main__":
    run()
else:
    logging.error("This file must be run directly, not imported.")
    sys.exit(1)
