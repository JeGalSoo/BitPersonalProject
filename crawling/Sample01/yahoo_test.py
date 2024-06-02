from abc import ABCMeta, abstractmethod
from csv import reader
import json
from fastapi import FastAPI
import googlemaps
import numpy as np
import pandas as pd
from  pandas_datareader import data as pdr
from sympy import sec
import uvicorn
import yfinance as yf
import matplotlib.pyplot as plt
import mysql.connector

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

class total: 
    def __init__(self):
        self.filename = ''

    def switch_case(self,filename):
        match filename:
            case '005930.KS':
                return '삼성 전자'
            case '000660.KS':
                return 'sk하이닉스'
            case '000990.KS':
                return 'DB하이텍'
            case '033640.KQ':
                return '네패스'
            case '093370.KS':
                return '후성'
            case '066570.KS':
                return 'LG전자'
            case '010120.KS':
                return 'LS ELECTRIC'
            case '118990.KQ':
                return '모트렉스'
            case '217820.KQ':
                return '원익피앤이'
            case '035510.KS':
                return '신세계I'

    def SearchStock(self):
        yf.pdr_override()
        # self.filename = input('종목코드를 입력하세요')
        # 코스피 종합주가 지수
        self.filename = '000990.KS'
        #삼전(005930.KS), sk하이닉스(000660.KS), DB하이텍(000990.KS), 네패스(033640.KQ), 
        #후성(093370.KS), LG전자(066570.KS), LS ELECTRIC(010120.KS), **요거없음 -> SK시그넷(260870.KS), 
        #모트렉스(118990.KQ), 원익피앤이(217820.KQ), 신세계I&C(035510.KS)
        #데이터를 가져올 시작날짜
        sdate = '2023-06-26'
        # #데이터를 가져올 최종날짜
        edate = '2024-05-29'

        # KOSPI종합주가지수를 가져오려면 가져올 데이터의
        # 1. 인덱스 문자열,
        # 2.시작일과 3.종료일을 넣어준다.
        # 종료일을 넣지 않을 경우 오늘 데이터까지 가져온다.
        a = pdr.get_data_yahoo(self.filename, sdate, edate)

        self.filename = self.switch_case(self.filename)


        
        a.rename(columns={'Date':'date','Open':'open','High':'high','Low':'low','Close':'close','Volume':'volume','Adj Close':'adj_close'},inplace=True)
        a.loc[:,"open"] = a["open"].map('{:,.0f}'.format)
        a.loc[:,"high"] = a["high"].map('{:,.0f}'.format)
        a.loc[:,"low"] = a["low"].map('{:,.0f}'.format)
        a.loc[:,"close"] = a["close"].map('{:,.0f}'.format)
        a.loc[:,"volume"] = a["volume"].map('{:,.0f}'.format)
        a.loc[:,"adj_close"] = a["adj_close"].map('{:,.0f}'.format)
        a.insert(loc=0,column='item',value=self.filename)
        a.to_csv(f'C:\\Users\\soo\\study\\새 폴더 (2)\\crawling\\Sample01\\{self.filename}.csv')
        print(a)
        # ---그래프보기----
        # plt.figure(figsize=(18, 7))
        # a['Close'].plot(label='KOSPI', title='KOSPI INDEX', grid=True, legend=True)#close y 축 kospi x축
        # plt.show()
        # ----------------
        #type(kospi)
        #참고 사이트 : https://velog.io/@ppppomi/23.10.23-%EC%A3%BC%EC%8B%9D%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B6%84%EC%84%9D


    def SaveDb(self):
        reader = Reader()
        data = reader.csv(f'C:\\Users\\soo\\study\\새 폴더 (2)\\crawling\\Sample01\\{self.filename}')
        print(data.Date)
        # fdata = pd.pivot_table(data, index='Date', aggfunc=np.sum)
        connection = mysql.connector.connect(
            host='localhost',
            port=3306,
            user='root',
            password='password',
            database='turingdb'
        )
        # 연결 확인
        if connection.is_connected():
            print("데이터베이스에 성공적으로 연결되었습니다.")
        # 쿼리 실행
            mycursor = connection.cursor()
        #     # mycursor.execute("CREATE TABLE items(item varchar(40), date varchar(20), open int(20), highint int(20), low int(20), close int(20), adj_close int(20), volume int(20))")
            query = "insert into items(item, date, open, high, low, close, adj_close, volume) values (%s, %s, %s, %s, %s, %s, %s, %s)"
            for i, row in data.iterrows():
                val = (row['item'], row['Date'],float(row['open']),float(row['high']),float(row['low']),float(row['close']),float(row['adj_close']),int(row['volume']))
                mycursor.execute(query, val)
                connection.commit()
            # print(mycursor.rowcount)
            connection.close()
        #   # 삽입된 데이터의 ID 출력
        # print("삽입된 데이터의 ID:", mycursor.lastrowid)


if __name__ == "__main__" :
    total = total()
    a = total.SearchStock()
    b = total.SaveDb()



