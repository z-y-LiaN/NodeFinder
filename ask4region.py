import requests
import sys
import time
from processor_db import *
requests.adapters.DEFAULT_RETRIES = 10
def ask4region(ip):
    s = requests.session()
    s.keep_alive = False
    str='http://ip.taobao.com/outGetIpInfo?ip='+ip+'&accessKey=alibaba-inc'
    #str='http://ip-api.com/json/'+ip
    response=requests.get(str)
    flag=1
    while flag:
        try:
            response_obj=response.json()
           # time.sleep(0.1)
        except:
            print(response,ip)
            #time.sleep(1)
        else:
            flag=0
    return response_obj['data']['country']
if __name__ == '__main__':
    dbconfig = {'sourcetable': 'ethereum', 'database': 'nodefinder_db', 'databaseip': 'localhost',
                'databaseport': 3306, 'databaseuser': 'root', 'databasepassword': '123456'}
    db = Db(dbconfig)
    db.connect()
    ips=db.execute("SELECT DISTINCT ip FROM ethereum")
    for ip in ips:
        country=ask4region(ip[0])
        ret = db.execute("SELECT country FROM ethereum WHERE ip='%s'" % (ip[0]))
        print(ret)
        db.execute("UPDATE ethereum SET country='%s' WHERE ip='%s'"%(country,ip[0]))
        ret=db.execute("SELECT country FROM ethereum WHERE ip='%s'"%(ip[0]))
        print(ret)
