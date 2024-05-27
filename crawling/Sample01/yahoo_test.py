from abc import ABCMeta, abstractmethod
from csv import reader
import json
import googlemaps
import numpy as np
import pandas as pd
from  pandas_datareader import data as pdr
from sympy import sec
import yfinance as yf
import matplotlib.pyplot as plt
import mysql.connector


# yf.pdr_override()
# # 코스피 종합주가 지수
# KOSPI_IDX = '^KS11'
# #KOSDAK_IDX ='^KQ11'
# #데이터를 가져올 시작날짜
# sdate = '2021-11-02'
# #데이터를 가져올 최종날짜
# edate = '2021-11-04'

# # KOSPI종합주가지수를 가져오려면 가져올 데이터의
# # 1. 인덱스 문자열,
# # 2.시작일과 3.종료일을 넣어준다.
# # 종료일을 넣지 않을 경우 오늘 데이터까지 가져온다.
# a = pdr.get_data_yahoo(KOSPI_IDX, sdate, edate)
# a.rename(columns={'Open':'open','High':'high','Low':'low','Close':'close','Volume':'volume','Adj Close':'adj_close'},inplace=True)
# a.to_csv(f'C:\\Users\\soo\\study\\새 폴더 (2)\\crawling\\Sample01\\asd.csv')

#type(kospi)
#https://velog.io/@ppppomi/23.10.23-%EC%A3%BC%EC%8B%9D%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B6%84%EC%84%9D

# 데이터베이스 연결 설정
class ReaderBase(metaclass=ABCMeta):
    @abstractmethod
    def csv(self):
        pass
    @abstractmethod
    def xls(self):
        pass
    @abstractmethod
    def json(self):
        pass
    @abstractmethod
    def gmaps(self):
        pass

class Reader(ReaderBase):
    def __init__(self) -> None:
        pass
    def csv(self, file) -> object:
        return pd.read_csv(f'{file}.csv', encoding='UTF-8', thousands=',')
    def xls(self, file, header, usecols) -> object:
        return pd.read_excel(f'{file}.xls', header=header, usecols=usecols)
    def json(self, file) -> object:
        return json.load(open(f'{file}.json', encoding='UTF-8'))
    def gmaps(self, api_key: str) -> object:
        return googlemaps.Client(key=api_key)
reader = Reader()
data = reader.csv(f'C:\\Users\\soo\\study\\새 폴더 (2)\\crawling\\Sample01\\asd')
print(data['open'])
connection = mysql.connector.connect(
    host='localhost',
    port=3306,
    user='root',
    password='password',
    database='turingdb'
)

# # 연결 확인
# if connection.is_connected():
#   print("데이터베이스에 성공적으로 연결되었습니다.")

if connection.is_connected():
    print("데이터베이스에 성공적으로 연결되었습니다.")
  # 쿼리 실행
    mycursor = connection.cursor()
    # mycursor.execute("CREATE TABLE items(item varchar(40), date varchar(20), open int(20), highint int(20), low int(20), close int(20), adj_close int(20), volume int(20))")
    query = "insert into items(item, date, open, highint, low, close, adj_close, volume) values (%s, %s, %s, %s, %s, %s, %s, %s)"
    val = ("코스피",'a',data['open'],data['high'],data['low'],data['close'],data['adj_close'],data['volume'])
    mycursor.execute(query, val)
    connection.commit()
    print(mycursor.rowcount)
    connection.close()

#   # 삽입된 데이터의 ID 출력
# print("삽입된 데이터의 ID:", mycursor.lastrowid)