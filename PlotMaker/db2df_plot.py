import MySQLdb
import pandas as pd

# sql은 외부에서 이 파일 실행시 제공되는 값

# 매개변수인 sql 내용에 따라 DB 검색 후 그 결과를 dataframe으로 리턴하는 함수 정의
def db2df(sql):
    conn = MySQLdb.connect(host='localhost', port=3306, db='mes', user='root', password='12345')
    return pd.read_sql(sql, conn)


# db2df 함수 테스트 (1)
sql = "select * from production limit 10"
df = db2df(sql)
print(df)

# db2df 함수 테스트 (2)
sql = "select * from quality limit 5"
df = db2df(sql)
print(df)


# 결과를 이용해 plot 그리기
# 
# 
# 