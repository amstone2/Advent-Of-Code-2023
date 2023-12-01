from word2number import w2n

def main():
    with open('input.txt') as f:

        lines = f.read().splitlines()
        
        total = 0
        for line in lines:
            number = int(getNumber(line))
            total += number
        print("Total: ", total)

def getNumber(line):
    line.replace(" ", "")

    firstNumber = 0
    while(True):
        if line[0].isnumeric():
            firstNumber = line[0]
            break
        elif(getFirstNumberFromStr(line) != 0):
            firstNumber = getFirstNumberFromStr(line)
            break
        line = line[1:]

    secondNumber = 0
    while(True):
        if line[-1].isnumeric():
            secondNumber = line[-1]
            break
        elif(getSecondNumberFromString(line) != 0):
            secondNumber = getSecondNumberFromString(line)
            break
        line = line[:-1]

    combinedNumber = str(firstNumber) + str(secondNumber)
    return combinedNumber

def getFirstNumberFromStr(str):
    validNumbers = ['one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']
    wordLen = 1
    number = 0
    while (wordLen < len(str)):
        subStr = str[0:wordLen]
        if(subStr in validNumbers):
            number = w2n.word_to_num(subStr)
            break
        wordLen = wordLen + 1
    return number

def getSecondNumberFromString(str):
    validNumbers = ['one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']
    wordLen = 1
    number = 0
    while (wordLen < len(str)):
        subStr = str[len(str) - wordLen: len(str)]
        if(subStr in validNumbers):
            number = w2n.word_to_num(subStr)
            break
        wordLen = wordLen + 1
    return number


if __name__ == "__main__":
    main()