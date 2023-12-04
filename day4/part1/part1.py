def main():
    with open('C:\\Users\\Alex\\Documents\\projects\\Advent-Of-Code-2023\\day4\\part1\\input.txt') as f:
        fileLines = f.read().splitlines()
        
        totalWinnings = 0

        for fileLine in fileLines: 

            cardWinnings = 0

            winningNumbers = fileLine.split(":")[1].split("|")[0].lstrip().rstrip().replace("  ", " ").split(" ")
            givenNumbers =   fileLine.split(":")[1].split("|")[1].lstrip().rstrip().replace("  ", " ").split(" ")

            for givenNumber in givenNumbers:
                if (givenNumber in winningNumbers):
                    if (cardWinnings == 0):
                        cardWinnings = 1
                    else:
                        cardWinnings = cardWinnings * 2
            
            totalWinnings += cardWinnings 
        
        print(totalWinnings)


if __name__ == "__main__":
    main()