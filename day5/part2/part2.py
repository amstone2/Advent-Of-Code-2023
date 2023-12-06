import sys

def main():
    with open('/Users/alexstone/Documents/git/Advent-Of-Code-2023/day5/part2/example.txt') as f:
        fileLines = f.read().splitlines()

        seeds = fileLines[0].replace("seeds: ", "").split(" ")
        seedRanges = []
        for i in range(0, len(seeds), 2):
            seedRanges.append(seeds[i] + " " +  seeds[i + 1])
              
        del fileLines[0:2]

        categories = []

        i = 0
        while(i < len(fileLines)):
            if "map" in fileLines[i]:                
                j = i + 1
                ranges = []
                while (True):
                    if(j == len(fileLines)):
                       i = j + 1
                       break
                    if (fileLines[j] == ""):
                        i = j + 1
                        break
                    ranges.append(fileLines[j])
                    j += 1
                categories.append(ranges)
        

        for seedRange in seedRanges:

            lowestNumber = float('inf')
            
            rangeStart = int(seedRange.split(" ")[0])
            length = int(seedRange.split(" ")[1])


            for i in range(rangeStart, rangeStart + length):
                destinationNumber = i
                for category in categories:
                    for r in category: 
                        num = getDestinationNumber(int(destinationNumber), r)
                        if(num != destinationNumber):
                            destinationNumber = num
                            break

                    if(destinationNumber < lowestNumber):
                        lowestNumber = destinationNumber


        print("lowestNumber", lowestNumber)

def getDestinationNumber(number, r):
    destinationRange = int(r.split(" ")[0])
    sourceRange = int(r.split(" ")[1])
    length = int(r.split(" ")[2])

    offset = 0




    if number >= sourceRange and number <= sourceRange + length:
        offset = number - sourceRange
        return destinationRange + offset 
    else:
        return number



    # if ((sourceRange + length - sourceNumber)  > 0):
    #     if (sourceNumber in range(sourceRange, sourceRange + length)):
    #         offset = sourceNumber - sourceRange
    #         return destinationRange + offset 
    #     else: 
    #         return sourceNumber
    # else: 
    #     return sourceNumber


if __name__ == "__main__":
    main()