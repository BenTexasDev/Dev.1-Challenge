#!/usr/bin/python
import re

# Will return to add comments on this program.
# Need to add add output and EOF. Some error checking as well.

fname = input('Enter the file name: ')
file = open('output.txt', 'w')
if len(fname) < 1 : fname = 'input.txt'
try:
	fhand = open(fname)
except:
	print ('File cannot be opened:',fname)
	exit()


di = dict()

for line in fhand:

    line = line.rstrip('\n')
    line = line.lower()
    line = re.sub('[,.]','', line)
    words = line.split()

    for w in words:
    	if w not in di:
    		di[w] = 1
    	else:
    		di[w] += 1

for w in sorted(di, key=di.get, reverse = True):
        file.write("{:8}{:2}{:5}{}".format(w,"|",di[w]*"=","("+str(di[w])+")""\n"))

file.close()