import random
import sys

def main():
  _min = int(sys.argv[1])
  _max = int(sys.argv[2])
  num = int(sys.argv[3])
  _range = _max-_min

  out = open("random_numbers", "wb");
 
  i = 0
  while (i < num):
    rand = random.randrange(_min, _max, 1)
    out.write("{0} ".format(rand))
    i += 1
  out.close(); 
main()

