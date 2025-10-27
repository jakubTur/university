import sys
import logging
import fileinput
"""Lab05

This script is a template of the assignment solution.

TODO: describe the puprose of your application here
"""

def createIPStructure(dictionaries):
    IPs={}
    for i in dictionaries.values():
        ip = i["ip"]
        if ip not in IPs:
            IPs[ip] = 1
            logging.info("new entry: " + str({ip: 1}))
        else:
            IPs[ip] += 1
            logging.info("updated entry: " + str({ip: IPs[ip]}))
    return IPs
def read_log(filename):
    dictionary={}
    counter=0
    print(f"Data from std input:")
    for line in fileinput.input(files=filename):
        logging.info("line: "+line)
        split=line.split('"')
        ipDate=split[0].split(' - - ')
        ip=ipDate[0]
        timestamp=(ipDate[1].strip('[')).strip('] ')
        request=split[1]
        numbers=split[2].split(' ')
        httpCode=numbers[1]
        size=numbers[2]
        referrer=split[3]
        userAgent=split[5]
        entry={
            'ip': ip,
            'timestamp' : timestamp,
            'request' : request,
            'HTTP code' : httpCode,
            'size' : size,
            'referer' : referrer,
            'userAgent' : userAgent
        }
        dictionary.update({f'entry {counter}' : entry})
        logging.info("entry added:"+ str(entry))
        counter+=1
    return dictionary
def run():


    def ip_requests_number(ip_address):
        return ips[ip_address]
    

    def ip_find(most_active=True):
        ret=[]
        val=0
        if(most_active):
            val=max(ips.values())
        else:
            val=min(ips.values())
        for ip, count in ips.items():
            if count==val:
                ret.append(ip)
        return ret


    print("This is lab 3 solution")
    accessLog=read_log('access_log')
    print("halo")
    print(str(accessLog))
    ips=createIPStructure(accessLog)
    print(ip_requests_number("161.35.44.203"))
    print(ip_find())
    print(ip_find(most_active=True))
    print(ip_find(most_active=False))

if __name__ == "__main__":
    logging.basicConfig(level=logging.DEBUG)
    logging.info('Start')
    run()
else:
    logging.error("Python file not ran directly")
    exit()