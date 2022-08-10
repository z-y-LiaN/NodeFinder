# 这是一个示例 Python 脚本。

# 按 Shift+F10 执行或将其替换为您的代码。
# 按 双击 Shift 在所有地方搜索类、文件、工具窗口、操作和设置。
import random
import math

import numpy
import pymysql
import networkx as nx
import aiomysql
import asyncio
import networkx as nx
import matplotlib.pyplot as plt
# 按间距中的绿色按钮以运行脚本。
import sha3

CMD_MY1=1
CMD_DEGREE=2
CMD_BETWEENNESS=3
CMD_MY2=4
CMD_MY3=5
CMD_RAND=6
CMD_MY4=7
CYCLE_TIME=60*60   #规定每个快照的周期时间 即这个快照是 60*60==3600秒=60分钟 这个时间段的探测情况
def buildnet(Nodes,Connections):
    G = nx.Graph()  # 创建空的网络图
    nodes = dict()
    Num = 0
    for i in Nodes:
        Num += 1
        nodes.update({i: Num})
        G.add_node(i)
    for i in Connections:
        if nodes.get(i[0]) and nodes.get(i[1]):
            G.add_edge(i[0], i[1])
    return G
def semi_local_centrality(G):
    N = {}
    Q = {}
    CL = {}
    for node in G.nodes:
        node_nei = list(G.neighbors(node))
        for n_i in node_nei:
            node_nei = node_nei + list(G.neighbors(n_i))
        node_nei = list(set(node_nei))
        N[node] = len(node_nei) - 1

    for node in G.nodes:
        node_nei = list(G.neighbors(node))
        t = 0
        for n_i in node_nei:
            t = t + N[n_i]
        Q[node] = t

    for node in G.nodes:
        node_nei = list(G.neighbors(node))
        t = 0
        for n_i in node_nei:
            t = t + Q[n_i]
        CL[node] = t
    '''
    for node in G.nodes:
        print(node, 'N-value:', N[node], 'Q-value:', Q[node], 'CL-value:', CL[node])
    '''
    return CL
def distinct(G):
    G.remove_nodes_from(list(nx.isolates(G)))
    return G
def net_analyzer(G,isdistinct=1):
    Degrees=nx.degree(G)
    nodes=nx.nodes(G)
    print("节点总数: "+len(nx.nodes(G)).__str__())
    if isdistinct:
        G.remove_nodes_from(list(nx.isolates(G)))
        print("节点总数（去除孤点）: " + len(nodes).__str__())
        C = sorted(nx.connected_components(G), key=len, reverse=True)
        len_list=list()
        for i in C:
            len_list.append(len(i))
            if len(len_list)!=1:
                G.remove_nodes_from(i)
    sum=0
    degrees=list()
    for i in Degrees:
        sum+=i[1]
        if(i[1]>4):
            degrees.append(i[1])
    print("平均度 "+(sum/len(nodes)).__str__())
    nx.degree_centrality(G)
    print("平均最短路径长度: "+nx.average_shortest_path_length(G).__str__())
    #print("degree_centrality: "+nx.degree_centrality(G).__str__())
    print("平均聚集系数: "+nx.average_clustering(G).__str__())
   # print("average_neighbor_degree: "+nx.average_neighbor_degree(G).__str__())
    print("网络直径: " + nx.diameter(G).__str__())
    print("度数大于4的节点数: "+len(degrees).__str__())
   # print(' '.join('%s' %each for each in degrees))

def keccak256(s):
    k = sha3.keccak_256()
    k.update(s)
    return k.digest()
def big_endian_to_int(value: bytes) -> int:
    return int.from_bytes(value, "big")
class Db:
    def __init__(self,dbconfig):
        self.dbconfig=dbconfig
    def connect(self):
        self.conn=pymysql.connect(host=self.dbconfig['databaseip'], port=self.dbconfig['databaseport'],
                                user=self.dbconfig['databaseuser'], password=self.dbconfig['databasepassword'], db=self.dbconfig['database'])
    def execute(self,sql,hasret=1):
        cursor=self.conn.cursor()
        cursor.execute(sql)
        if hasret:
            return cursor.fetchall()
    def close(self):
        self.conn.close()

def minmax_scale(data):
    maxn=max(data.values())
    minn=min(data.values())
    for i in data:
        data.update({i:data.get(i)/(maxn-minn)})
    return maxn,minn,data
def sigmoid(data):
    for i in data:
        data.update({i:1/(1+math.exp(-data.get(i)))})
    return data
def _get_handler(cmd):
    if cmd == CMD_MY1:
        return my1_Importance
    elif cmd == CMD_MY2:
        return  my2_Importance
    elif cmd == CMD_MY3:
        return  my3_Importance
    elif cmd == CMD_DEGREE:
        return  degree_Importance
    elif cmd == CMD_BETWEENNESS:
        return betweenness_Importance
    elif cmd == CMD_RAND:
        return random_Importance
    elif cmd == CMD_MY4:
        return my4_Importance
def random_Importance(G,db):
    random_importance = dict()
    for node in G.nodes:
        random_importance.update({node:random.randint(0,1)})
    L = list(random_importance.items())
    L.sort(key=lambda x: x[1], reverse=True)
    return L
def betweenness_Importance(G,db):
    betweenness_importance = nx.betweenness_centrality(G)
    L = list(betweenness_importance.items())
    L.sort(key=lambda x: x[1], reverse=True)
    return L
def degree_Importance(G,db):
    degree_importance = nx.degree_centrality(G)
    L = list(degree_importance.items())
    L.sort(key=lambda x: x[1], reverse=True)
    return L
def my1_Importance(G,db):
   # CL = semi_local_centrality(G)
    NUM = db.execute("SELECT MAX(RECV_NUM) FROM ethereum_neighbours")
    NUM = NUM[0][0]
    Activity = getchange(NUM, G.nodes, db)
    maxn, minn, Activity = minmax_scale(Activity)
    Activity1 = Activity
    importance = dict()
    for i in G.nodes:
        importance.update({i: Activity1.get(i) })
    L = list(importance.items())
    L.sort(key=lambda x: x[1], reverse=True)
    return L
def my2_Importance(G,db):
   # CL = semi_local_centrality(G)
    NUM = db.execute("SELECT MAX(RECV_NUM) FROM ethereum_neighbours")
    NUM = NUM[0][0]
    Activity = getchange(NUM, G.nodes, db)
    maxn, minn, Activity = minmax_scale(Activity)
    Activity1 = sigmoid(Activity)
    cluster=dict()
    for i in G.nodes:
        cluster.update({i:math.exp(-nx.clustering(G,i))})
    importance = dict()
    for i in G.nodes:
        value=0
        for nei in nx.neighbors(G,i):
            value+=nx.clustering(G,nei)
        importance.update({i: Activity1.get(i)  * value})
    L = list(importance.items())
    L.sort(key=lambda x: x[1], reverse=True)
    return L
def my3_Importance(G,db):
    CL = semi_local_centrality(G)
    NUM = db.execute("SELECT MAX(RECV_NUM) FROM ethereum_neighbours")
    NUM = NUM[0][0]
    Activity = getchange(NUM, G.nodes, db)
    maxn, minn, Activity = minmax_scale(Activity)
    Activity1 = Activity
    cluster=dict()
    for i in G.nodes:
        cluster.update({i:math.exp(-nx.clustering(G,i))})
    importance = dict()
    for i in G.nodes:
        value=0
        for nei in nx.neighbors(G,i):
            value+=nx.clustering(G,nei)
        importance.update({i: Activity1.get(i)*value* cluster.get(i)})
    L = list(importance.items())
    L.sort(key=lambda x: x[1], reverse=True)
    return L
def my4_Importance(G,db):
    NUM = db.execute("SELECT MAX(RECV_NUM) FROM ethereum_neighbours")
    NUM = NUM[0][0]
    cluster=dict()
    for i in G.nodes:
        cluster.update({i:math.exp(-nx.clustering(G,i))})
    importance = dict()
    for i in G.nodes:
        value=0
        for nei in nx.neighbors(G,i):
            value+=nx.clustering(G,nei)
        importance.update({i:value* cluster.get(i)})
    L = list(importance.items())
    L.sort(key=lambda x: x[1], reverse=True)
    return L
def gen_importance(G,db,percent,method):
    handler=_get_handler(method)
    L=handler(G,db)
    G_tmp = G.copy()
    for i in range(int(len(G.nodes)*percent)):
        G_tmp.remove_node(L[i][0])
    #net_analyzer(G_tmp)
    return G_tmp
def rev_scale(maxn,minn,data):
    for i in data:
        data.update({i:data.get(i)*(maxn-minn)})
    return data
def getchange(NUM,nodeSet,db):
    '''
            获取指定节点集的 节点 的邻居变化情况 来作为节点积极度的衡量
    :param NUM:  我们动态拓扑 的快照的个数 （int）
    :param nodeSet: 指定节点集  (set)
    :param db: 数据库指针
    :return: change_list (dict) key=str(节点名) value=int(积极度)
    '''
    prelist = dict()  #该节点前一个快照的邻居
    change_list=dict()  #该节点 每两个快照间 的邻居变化量
    '''
        初始化 prelist  即 置入第一个快照的邻居情况
        初始化 changelist 每个节点置一个空list
    '''
    for node_nei in nodeSet:
        sql = "SELECT DISTINCT nodeid2 from ethereum_neighbours where nodeid1='%s' AND RECV_NUM='%d'" % (node_nei, 1)
        nodelist = db.execute(sql)
        Setnode = set()
        for node in nodelist:
            Setnode.add(node[0])
        prelist.update({node_nei:Setnode})
        change_list.update({node_nei:list()})
    '''
        对于 第i个快照 遍历 指定节点集（nodeSet）的每个节点 获得其在该快照中的邻居情况
        与前一个快照进行比较
    '''
    for i in range(2, NUM + 1):#对于第i个快照
        for nodeid1 in nodeSet:#遍历指定节点集（nodeSet）
            sql = "SELECT DISTINCT nodeid2 from ethereum_neighbours where nodeid1='%s' AND RECV_NUM='%d'" % (nodeid1, i)
            nodelist = db.execute(sql)#获得其在该快照中的邻居情况
            '''
                数据处理成set
            '''
            setnode = set()
            for node in nodelist:
                setnode.add(node[0])
            '''
                更新change_list
            '''
            tmp_List=change_list.get(nodeid1)
            preset=prelist.get(nodeid1)
            '''
                积极度计算方法  （前一个快照的邻居节点集合 并 该快照的邻居节点集合）减 （前一个快照的邻居节点集合 交 该快照的邻居节点集合）
            '''
            tmp_List.append(len((setnode|preset)-(setnode&preset)))
            change_list.update({nodeid1:tmp_List})
            '''
                更新prelist
            '''
            prelist.update({nodeid1: setnode})
    for node in nodeSet:
        change_list.update({node:sum(change_list.get(node))/NUM})
        #print(change_list.get(node),node)
    return change_list

def getdata3(db):
    '''
            获取 id 到 ip 与  ip到 id 的映射情况
    :param db: 数据库指针
    :return:    id2ips id->（ip的集合）  是一个多射
                ip2ids ip->（is的集合）  是一个多射
    '''
    total=db.execute("SELECT DISTINCT nodeid,ip FROM ethereum")
    ips=dict()
    ids=dict()
    for i in total:
        if ips.get(i[0]):
            tmp=ips.get(i[0])
            tmp.add(i[1])
            ips.update({i[0]:tmp})
        else:
            ips.update({i[0]:{i[1]}})
        if ids.get(i[1]):
            tmp=ids.get(i[1])
            tmp.add(i[0])
            ids.update({i[1]:tmp})
        else:
            ids.update({i[1]:{i[0]}})
    id2ips=list()
    ip2ids=list()
    lenip=0
    lenid=0
    for i in ips:
        try:
            if len(ips.get(i))>1:
                id2ips.append(len(ips.get(i)))
                lenip+=len(ips.get(i))
        except:
            print(i)
    for i in ids:
        try:
            if len(ids.get(i)) > 1:
                ip2ids.append(len(ids.get(i)))
                lenid+=len(ids.get(i))
        except:
            print(i)
    return lenip,lenid,id2ips,ip2ids
if __name__ == '__main__':
    dbconfig = {'sourcetable': 'ethereum', 'database': 'nodefinder_db', 'databaseip': 'localhost',
                'databaseport': 3306, 'databaseuser': 'root', 'databasepassword': '123456'}
    db=Db(dbconfig)
    db.connect()
    lenip,lenid,id2ips,ip2ids=getdata3(db)
    print("探测到的所有节点:"+db.execute("SELECT COUNT(DISTINCT nodeid)  FROM ethereum")[0][0].__str__())
    print("IP地址变化的节点:"+lenip.__str__())
    print("探测到的IP地址:"+db.execute("SELECT COUNT(DISTINCT ip) FROM ethereum")[0][0].__str__())
    print("运行多个节点的IP地址:"+lenid.__str__() )
    active = db.execute("SELECT DISTINCT nodeid FROM ethereum_active_nodes")
    print("活跃节点总数:"+len(active).__str__())
    nodes=set()
    for i in active:
        nodes.add(i[0])
    #print("探测到的所有节点（从路由表中探测到的节点+被PING时增加的节点）: "+db.execute("SELECT COUNT(DISTINCT nodeid) FROM ethereum")[0][0].__str__())
    route_node = db.execute("SELECT DISTINCT nodeid1  FROM ethereum_neighbours")
    #print("有路由表的节点: "+len(route_node).__str__())
    route=set()
    for i in route_node:
        route.add(i[0])
    dis = db.execute("SELECT DISTINCT nodeid2  FROM ethereum_neighbours WHERE RECV_NUM='%d'" % (1))
   # print("在路由表中出现的节点 的数量(去重): "+len(dis).__str__())
    all = db.execute("SELECT  nodeid2  FROM ethereum_neighbours WHERE RECV_NUM='%d'" % (1))
   # print("在路由表中出现的所有节点 的数量（不包含收到PING时增加的节点）: "+len(all).__str__())

    '''
        获得重复的节点 有重复代表 度>1
        对于度==1的点 它是不能路由的 因此我们认为它对网络没贡献
    '''

    new_dis = set()
    diss=set()
    new_all=set()
    for i in dis:
        new_dis.add(i[0])
        diss.add(i[0])
    new_all = set()
    for i in all:
        if {i[0]} & new_dis:
            new_dis.remove(i[0])
        else:
            new_all.add(i[0])
    print("可路由节点:"+len(new_all).__str__())
    conns = db.execute("SELECT DISTINCT nodeid1,nodeid2  FROM ethereum_neighbours WHERE RECV_NUM='%d'" % (1))
    num=list()
    G=buildnet(nodes,conns)
    G2=buildnet((new_all|route),conns)
    G3=buildnet((new_all|route)&nodes,conns)
    '''
     print("--------------------------------")
    print("活跃节点（有pong回应的节点）组成的网络")
    net_analyzer(G)
    print("--------------------------------")
    print("可路由节点 | 有路由表的节点  组成的网络")
    net_analyzer(G2)
    print("--------------------------------")
    print("(可路由节点 | 有路由表的节点) & 活跃节点  组成的网络")
    
    '''
    net_analyzer(G3)
    #id2region=mapID2region(G3,db)

    '''
    getjson.createjson('init_data_MY1_1%', gen_importance(G3, db, 0.01, CMD_MY1), id2region)
    getjson.createjson('init_data_MY1_2%', gen_importance(G3, db, 0.02, CMD_MY1), id2region)
    getjson.createjson('init_data_MY1_3%', gen_importance(G3, db, 0.03, CMD_MY1), id2region)
    getjson.createjson('init_data_MY1_5%', gen_importance(G3, db, 0.05, CMD_MY1), id2region)
    getjson.createjson('init_data_MY2_1%', gen_importance(G3, db, 0.01, CMD_MY2), id2region)
    getjson.createjson('init_data_MY2_2%', gen_importance(G3, db, 0.02, CMD_MY2), id2region)
    getjson.createjson('init_data_MY2_3%', gen_importance(G3, db, 0.03, CMD_MY2), id2region)
    getjson.createjson('init_data_MY2_5%', gen_importance(G3, db, 0.05, CMD_MY2), id2region)
    getjson.createjson('init_data_MY3_1%', gen_importance(G3, db, 0.01, CMD_MY3), id2region)
    getjson.createjson('init_data_MY3_2%', gen_importance(G3, db, 0.02, CMD_MY3), id2region)
    getjson.createjson('init_data_MY3_3%', gen_importance(G3, db, 0.03, CMD_MY3), id2region)
    getjson.createjson('init_data_MY3_5%', gen_importance(G3, db, 0.05, CMD_MY3), id2region)
    getjson.createjson('init_data_DEGREE_1%', gen_importance(G3, db, 0.01, CMD_DEGREE), id2region)
    getjson.createjson('init_data_DEGREE_2%', gen_importance(G3, db, 0.02, CMD_DEGREE), id2region)
    getjson.createjson('init_data_DEGREE_3%', gen_importance(G3, db, 0.03, CMD_DEGREE), id2region)
    getjson.createjson('init_data_DEGREE_5%', gen_importance(G3, db, 0.05, CMD_DEGREE), id2region)
    getjson.createjson('init_data_BETWEENNESS_1%', gen_importance(G3, db, 0.01, CMD_BETWEENNESS), id2region)
    getjson.createjson('init_data_BETWEENNESS_2%', gen_importance(G3, db, 0.02, CMD_BETWEENNESS), id2region)
    getjson.createjson('init_data_BETWEENNESS_3%', gen_importance(G3, db, 0.03, CMD_BETWEENNESS), id2region)
    getjson.createjson('init_data_BETWEENNESS_5%', gen_importance(G3, db, 0.05, CMD_BETWEENNESS), id2region)

    '''

    for i in route_node:
        sql="SELECT DISTINCT nodeid2 from ethereum_neighbours where nodeid1='%s' AND RECV_NUM='%d'" % (i[0],1)
        nodes =db.execute(sql)
        '''
        if len(nodes)<=17*16:
            num.append(len(nodes))
        '''
        num.append(len(nodes))
        bucket = set()
        for node in nodes:
            if int(node[0], 16) ^ int(i[0], 16) == 0 or int(math.log2(int(node[0], 16) ^ int(i[0], 16)) - 239) < 0:
                bucket.add(0)
                continue
            bucket.add(int(math.log2(int(node[0], 16) ^ int(i[0], 16)) - 239))
        #print(bucket)
        #print(i[0])
        #print(len(nodes))
    print("平均路由表大小: " + round(len(conns) / len(route_node),3).__str__())

    print("平均路由表大小: "+(len(conns) / len(route_node)).__str__())
    n, bins,patchs=plt.hist(num, bins=5)
    for i in range(0,len(n)):
        print(numpy.int(n[i]),end=" ")
    print("")
    for i in range(1,len(bins)):
        print(numpy.float(bins[i]),end=" ")
    print("")
    plt.xlabel("connections")
    plt.ylabel("nodes")
    plt.title("nodes in routing table distribution")
    plt.show()


    db.close()
