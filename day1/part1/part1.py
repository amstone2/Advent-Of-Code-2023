def main():
    with open('input.txt') as f:
        lines = f.readlines()
        
        total = 0
        for line in lines:
            number = int(getNumber(line))
            total += number
        print(total)

def getNumber(line):
    firstNumber = 0
    for char in line:
        if char.isnumeric():
            firstNumber = char
            break
    
    secondNumber = 0
    for char in reversed(line):
        if char.isnumeric():
            secondNumber = char
            break

    combinedNumber = firstNumber + secondNumber

    return combinedNumber


if __name__ == "__main__":
    main()