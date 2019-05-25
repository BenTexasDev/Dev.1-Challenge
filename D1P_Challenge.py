#!/usr/bin/python
import re

# Will return to add comments on this program.
# Need to add add output and EOF. Some error checking as well.

fname = input('Enter the file name: ')
if len(fname) < 1 : fname = 'input.txt'

# try to open file
try:
	fhand = open(fname)
# If file does not exist or can't be opened throw an error	
except:
	print ('Error:File cannot be found:', fname)
	exit()
# Create output file after file has been opened with write flag.
outputfile = open('output.txt', 'w')

# Create dictionary to store each word, line by line.
di = dict()
# Iterate through each line in the file.
for line in fhand:
	# We want to strip newline.
    line = line.rstrip('\n')
	# Returns copy of the string in which all case-based characters have been lowercased.
    line = line.lower()
	# Sub special characters in the file with nothing. (Delete the file)
    line = re.sub('[,.!@#]','', line)
	# Split line into list of strings
    words = line.split()
	# Iterate through the list and count occurences of each word. di[w] = occurences,
	# Take those occurences and store in the dictionary.
    for w in words:
    	if w not in di:
    		di[w] = 1
    	else:
    		di[w] += 1
# Sorted iterates over the dictionary keys, using the number of word occurences as a sort key.
for w in sorted(di, key=di.get, reverse = True):
        outputfile.write("{:8}{:2}{:5}{}".format(w,"|",di[w]*"=","("+str(di[w])+")""\n"))

# Close output file
outputfile.close()