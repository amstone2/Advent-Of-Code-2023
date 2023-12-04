def main():
    with open('C:\\Users\\Alex\\Documents\\projects\\Advent-Of-Code-2023\\day4\\part1\\input.txt') as f:
        fileLines = f.read().splitlines()
        
        cardList = []

        for i, fileLine in enumerate(fileLines): 

            winningNumbers = fileLine.split(":")[1].split("|")[0].lstrip().rstrip().replace("  ", " ").split(" ")
            givenNumbers =   fileLine.split(":")[1].split("|")[1].lstrip().rstrip().replace("  ", " ").split(" ")

            cardList.append(card(i + 1, 1, winningNumbers, givenNumbers))     

        for i, c in enumerate(cardList):
            matchingNumebrs = c.MatchingNumebrs()

            startIndex = i + 1
            endIndex = startIndex + matchingNumebrs

            iterations = cardList[i].count

            while (iterations != 0 ):

                for j in range(startIndex, endIndex, 1):
                    if (j < len(cardList)):
                        prevCount = cardList[j].count 
                        newCount = prevCount + 1
                        cardList[j].count = newCount    
                iterations -= 1

        cardTotal = 0
        for c in cardList:
            cardTotal += c.count
        
        print("Card total:", cardTotal)

class card:
    def __init__(self, number, count, winningNumbers, givenNumbers):
        self.number = number
        self.count = count
        self.winningNumbers = winningNumbers
        self.givenNumbers = givenNumbers

    def MatchingNumebrs(self):
        total = 0
        for givenNumber in self.givenNumbers:
            if (givenNumber in self.winningNumbers):
                total += 1

        return total        

if __name__ == "__main__":
    main()