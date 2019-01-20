import unicodedata

print("Just a delta: " + u'\u0394')  # for hexidecimal values
print("And another: " + chr(916)) # for decimal values
# because 394 in base 16 = 3*16^2 + 9*16 + 4 = 916 in base 10

nummy=u'\u0394'
print("And its unicode name is: "+unicodedata.name(nummy))

print("Here is a checkmark: " + u'\u2713')
print("And its unicode name is: "+unicodedata.name(u'\u2713'))
print("And the inverted question mark: " + chr(191))
print("And its unicode name is: "+unicodedata.name(u'\u00BF'))
# because 191 in base 10 is BF in base 16
print("Or like this: "+unicodedata.name(chr(191)))

print()

# Greek lower case from 945 through 969
greek_letters_low=[chr(code) for code in range(945,970)]
print("GREEK LOWER CASE")
print(greek_letters_low)

# Greek lower case from 913 through 937 (930 has none)
greek_letters_hi=[chr(code) for code in range(913,938)]
print("GREEK UPPER CASE")
print(greek_letters_hi)

# Basic Cyrillic lower case
basic_cyrillic_low=[chr(code) for code in range(1072,1104)]
print("CYRILLIC LOWER CASE")
print(basic_cyrillic_low)

# Basic Cyrillic upper case
basic_cyrillic_hi=[chr(code) for code in range(1040,1072)]
print("CYRILLIC UPPER CASE")
print(basic_cyrillic_hi)