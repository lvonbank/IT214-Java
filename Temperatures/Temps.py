##
#  This program reads a file whose lines contain dates,
#  high temperatures, low temperatures:
#  date 1, high, low
#  date 2, high, low
#  ...
#  The values are separated by commas.
#  The program writes a file in which the dates are left-aligned and the
#  temperatures are right-aligned. The last line has the average high temperature
#

# Prompt for the input and output file names.
inputFileName = input("Input file: ")
outputFileName = input("Output file: ")

# Open the input and output files.
inputFile = open(inputFileName, "r")
outputFile = open(outputFileName, "w")

# Set the totals for averaging to zero
totalHigh = 0
totalLow = 0

# Read the input and write the output.
  
for line in inputFile :   
   # Make sure there is a comma in the input line, otherwise skip the line.
   if "," in line :
      # Split the record at the commas
      # print(line)
      parts = line.split(",")

      # Extract the three data fields.
      date = parts[0]
      high = int(parts[1])
      low = int(parts[2])
      
      # Increment the total of high temperatures
      totalHigh = totalHigh + high
      totalLow = totalLow + low
      
      # Write the output.
      outputFile.write('{:10}{:>5}{:>5}'.format (date,parts[1],parts[2]))

# Write the average high and low.
outputFile.write("%-28s%5.2f\n" % ("\nAverage high temperature:", float(totalHigh)/365.00))
outputFile.write("%-28s%5.2f\n" % ("\nAverage low temperature:", float(totalLow)/365.00))

# Close the files.
inputFile.close()
outputFile.close()
