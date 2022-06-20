import requests
import sys

def ask4region(ip):
    s = requests.session()
    s.keep_alive = False
   # str='http://ip.taobao.com/outGetIpInfo?ip='+ip+'&accessKey=alibaba-inc'
    str='http://ip-api.com/json/'+ip
    response=requests.get(str)
    response_obj=response.json()
    return response_obj['country']
if __name__ == '__main__':
    ip = []
    for i in range(1, len(sys.argv)):
        ip.append(sys.argv[i])
    print(ask4region(ip[0]))