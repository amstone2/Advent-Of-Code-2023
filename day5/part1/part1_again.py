import sys

def main():
    with open('/Users/alexstone/Documents/git/Advent-Of-Code-2023/day5/part1/input.txt') as f:
        fileLines = f.read().splitlines()
        seeds = fileLines[0].replace("seeds: ", "").split(" ")
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

        catDictionaries = []
        for cat in categories:
            dictionary = {}
            for r in cat:
                destinationRange = int(r.split(" ")[0])
                sourceRange = int(r.split(" ")[1])
                length = int(r.split(" ")[2])

                offset = 0
                index = sourceRange
                while index < (sourceRange + length):
                    dictionary[index] = destinationRange + offset
                    offset += 1
                    index += 1
            catDictionaries.append(dictionary)


        lowestNumber = float('inf')
        for seed in seeds:
            destinationNumber = seed

            for catDict in catDictionaries:
                if int(destinationNumber) in catDict.keys():
                    destinationNumber = catDict[int(destinationNumber)]

            if int(destinationNumber) < lowestNumber:
                lowestNumber = int(destinationNumber)

            print("lowestNumber", lowestNumber)


        # lowestNumber = float('inf')
        # for seed in seeds:
        #     destinationNumber = seed
        #     for cat in categories:
        #         for r in cat.ranges: 
        #             num = getDestinationNumber(destinationNumber, r)
        #             if(num != destinationNumber):
        #                 destinationNumber = num
        #                 break

        #     if(destinationNumber < lowestNumber):
        #         lowestNumber = destinationNumber

        # print("lowestNumber", lowestNumber)

def getDestinationNumber(sourceNumber, r):
    destinationRange = int(r.split(" ")[0])
    sourceRange = int(r.split(" ")[1])
    length = int(r.split(" ")[2])

    offset = 0
    if (int(sourceNumber) in range(sourceRange, sourceRange + length)):
        offset = int(sourceNumber) - sourceRange
        return destinationRange + offset 
    else: 
        return sourceNumber


if __name__ == "__main__":
    main()