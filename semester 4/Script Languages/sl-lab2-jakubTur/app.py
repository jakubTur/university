import sys
import logging
logging.basicConfig(level=logging.DEBUG)
logging.info('Start')

largestPath=None
failedRequests=0
totalBytes=0
biggestSize=-1
totalProcessingTime=0
biggestProcessingTime=0
amount=0

for line in sys.stdin:
    amount+=1
    components=line.split()
    if(len(components)<4):
        logging.warning(f'not enough components in line {amount}')
        continue
    elif(len(components)>4):
        logging.warning(f'too many components in line {amount}')
        continue
    path, httpCode, sentBytes, processingTime = components
    print(path)
    if int(httpCode)==404:
        logging.debug('404-page not found')
        print("!", end='')
        failedRequests+=1
    if(int(sentBytes)>biggestSize):
        largestPath=path
        logging.debug(f"the size of the biggest path changed to: {sentBytes} from: {biggestSize}")
        biggestProcessingTime=int(processingTime)
        biggestSize=int(sentBytes)
    totalProcessingTime+=int(processingTime)
    totalBytes+=int(sentBytes)
    logging.debug(f'total processing time increased to: {totalProcessingTime}')
    logging.debug(f'total size in bytes increased to: {totalBytes}')
    
print("Path of the largest resource: "+str(largestPath))
print("Processing time of the largest resource: "+str(biggestProcessingTime))
print("Failed requests: "+str(failedRequests))
print("Total numbers of bytes: "+str(totalBytes))
print("Total numbers of kilobytes: "+str(totalBytes/1024))
print("Average processing time: "+str(totalProcessingTime/amount))
logging.info("Finished")