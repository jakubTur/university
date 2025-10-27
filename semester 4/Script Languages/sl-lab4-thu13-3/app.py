import sys
import logging
import datetime

class logEntry: #task 5
    def __init__(self,line):
        self.fullLog=line
        self.ip, self.dateAndTime, self.path, self.code, self.bytes, self.time=line.split(" ")
        self.datetime=newDatetime(self.dateAndTime)
    def bytesInt(self):
        return int(self.bytes)
    def codeInt(self):
        return int(self.code)
    def __str__(self):
        return self.fullLog
    def __repr__(self):
        return f"IP - {self.ip}\npath - {self.path}\ncode - {self.code}\n no. bytes - {self.bytes}\n time - {self.time}\ndate and time - {self.dateAndTime}"
    def htmlFail(self):
        return (self.code[0]=='4' or self.code[0]=='5')

def createLogEntry(line): #task 6
    return logEntry(line)

def createLogFile(s): # task 7
    objects=[]
    for line in s:
        objects.append(createLogEntry(line))
    return objects

def newDatetime(line): #task 4
    date, time=line.split(':',1)
    day,monthString,year=date.split('/')
    hour,minute,second=time.split(':')
    monthMap={
        'Jan': 1, 'Feb': 2, 'Mar': 3, 'Apr': 4,
        'May': 5, 'Jun': 6, 'Jul': 7, 'Aug': 8,
        'Sep': 9, 'Oct': 10, 'Nov': 11, 'Dec': 12
    }
    month=monthMap[monthString]
    dateTimeObj=datetime.datetime(int(year),month,int(day),int(hour),int(minute),int(second))
    return dateTimeObj

#Task8
def display(moment1, moment2, datetimes, lines):
    if moment1 > moment2:
        logging.warning("Error, invalid sequence of moments")
        return
    for i in range(len(datetimes)):
        if moment1 <= datetimes[i] <= moment2:
            print(lines[i].strip())

def run():
    
    """Document each function
    
    Parameters:
    none
    Returns:
    nothing
    """
    print("This is lab 3 solution")
    s = sys.stdin.readlines()
    print(f"Data from std input: {s}")
    list=createLogFile(s)
    print(list[0])
    datetimes=[]
    counter=0
    moment1 = datetime.datetime(2020,10,18,4,20)
    moment2 = datetime.datetime(2020,10,18,12,0)
    for line in s:
        counter+=1
        content=line.split(" ")
        if(len(content)==6):
            datetimes.append(newDatetime(content[1]))
        else:
            logging.warning(f"Not enough elements in line {counter}")
    
    display(moment1,moment2,datetimes,s)


if __name__ == "__main__":
    logging.basicConfig(level=logging.DEBUG)
    logging.info('Start')
    run()
else:
    logging.error("Python file not ran directly")
    exit()