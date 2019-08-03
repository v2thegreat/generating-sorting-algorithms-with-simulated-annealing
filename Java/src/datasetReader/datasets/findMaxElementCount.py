from sys import argv, getsizeof
from getSize import getsize
from math import factorial

a = 8	#Tuple Element Overhead
b =	8	#List Element Overhead

tupleOverhead = 56
listOverhead = 72

def getsize3(n, m = None):
	m = factorial(n) if m == None else m
	tupleBytes = a * n * (8 + tupleOverhead)
	return (tupleBytes * b * m + listOverhead)

def getIdealElementSize(n, avalible, r = 0.5):
	return (r * avalible - listOverhead)/(a*n*(8 + tupleOverhead) * b)

maxSize = 10**9/2

# if '-n' in argv:
# 	n = int(argv[2])
# else:
# 	n = int(input("Enter the partition number: "))

print(getIdealElementSize(13, maxSize))
