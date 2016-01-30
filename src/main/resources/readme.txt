Then we can start installing HAProxy and Keepalived:
shell> yum install haproxy keepalived socat

shell> chkconfig haproxy on
shell> chkconfig keepalived on

We can check the installed HAProxy and Keepalived versions as follows:
shell> haproxy -v
HA-Proxy version 1.5.2 2014/07/12

shell> keepalived --version
Keepalived v1.2.13 (10/15,2014)


mysql -u root -h 11.11.11.101 -pvagrant -e "show variables like 'wsrep_node_name';"


http://www.thenoccave.com/2013/12/30/mariadb-galera-cluster-ha-proxy-keepalived-centos-6/
https://mariadb.com/blog/setup-mariadb-enterprise-cluster-part-3-setup-ha-proxy-load-balancer-read-and-write-pools
http://fanli7.net/a/bianchengyuyan/C__/20140116/462143.html
http://www.fromdual.com/making-haproxy-high-available-for-mysql-galera-cluster
http://www.networkinghowtos.com/howto/viewing-haproxy-statistics/
http://tecadmin.net/how-to-configure-haproxy-statics/

http://blog.cloudpack.jp/2015/01/06/connect-to-galera-cluster-mariadb-via-haproxy/