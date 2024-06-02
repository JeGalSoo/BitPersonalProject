# -*- coding: utf-8 -*-
"""
Created on Tue Feb 15 07:56:54 2022
"""
#kis_api module 을 찾을 수 없다는 에러가 나는 경우 sys.path에 kis_api.py 가 있는 폴더를 추가해준다.
import kis_auth as ka
import kis_domstk as kb

import pandas as pd

import sys

# 토큰 발급
ka.auth()
# [국내주식] 기본시세 > 국내주식기간별시세(일/주/월/년) (현재)  (종목번호 6자리)
rt_data = kb.get_inquire_daily_itemchartprice(div_code='J', itm_no="118990",tr_cont='', inqr_strt_dt='20230805', inqr_end_dt='20230809',period_code="D", adj_prc='1')