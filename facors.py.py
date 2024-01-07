n=int(input())
#import time
#p=time.time()
for i in range(1,int(n**0.5)+1) :
    if(n%i==0) :
        print(i,end=" ")
        print(n//i,end=' ')
#print()
#q=time.time()
#print(q-p)