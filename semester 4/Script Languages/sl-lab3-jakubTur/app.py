import sys
import logging
logging.basicConfig(level=logging.DEBUG)
logging.info('Start')

def print_html_entries(entries):
    print(html_entries(entries))

def html_entries(entries):
    return list(filter(lambda x: str(x[0]).endswith('.html'), entries))

def successful_reads(entries):
    logging.debug(f"Number of entries in the list:{len(entries)}")
    return list(filter(lambda x: str(x[1]).startswith('2'), entries))

def failed_reads(entries):
    entries4=list(filter(lambda x: str(x[1]).startswith('4'), entries))
    entries5=list(filter(lambda x: str(x[1]).startswith('5'), entries))
    logging.debug(f"There are {len(entries4)} entries starting with 4")
    logging.debug(f"There are {len(entries5)} entries starting with 5")
    return entries4+entries5

def read_log(text):
    lineAmount=0
    entriesAmount=0
    for line in text:
        content=line.split(" ")
        if(len(content)==4):
            text[entriesAmount]=((content[0], int(content[1]), int(content[2]), int(content[3])))
            entriesAmount+=1
        lineAmount+=1
    logging.debug(f"Number of lines read:{lineAmount}")
    logging.debug(f"Number of entries in the list:{entriesAmount}")
    return text[:-(lineAmount-entriesAmount)]

def run():
    print("This is lab 3 solution")
    s = sys.stdin.readlines()
    print(f"Data from std input: {s}")
    readLog=read_log(s)
    print(f"The log reads: \n{readLog}")
    print(f"Successful reads from the table are: \n{successful_reads(readLog)}")
    print(f"Unuccessful reads from the table are: \n{failed_reads(readLog)}")
    print("Successfully retrieved pages with .html extension:")
    print_html_entries(readLog)
    
if __name__ == "__main__":
    run()
else:
    logging.error("Python file not ran directly")
    exit()