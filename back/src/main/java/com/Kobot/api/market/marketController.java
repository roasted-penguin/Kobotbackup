package com.Kobot.api.market;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URL;
import java.net.URI;
import java.net.http.*;

@Controller
@RequestMapping("/update-data")
public class marketController {

    @RequestMapping("minute")
    public void main(){

    }
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.upbit.com/v1/candles/minutes/1?market=KRW-BTC&count=1"))
            .header("accept", "application/json")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
    HttpResponse<String> response;

    {
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //System.out.println(response.body());
}
//import requests
//        import pymysql
//        import json
//        import pandas as pd
//        import csv
//        from urllib import parse
//
//        coin_input = input("Coin input : ")
//        last_date_encoded = ""
//        result = pd.DataFrame()
//
//        db = pymysql.connect(host='localhost',
//        user='root',
//        password='######',
//        db='upbitDB',
//        charset='utf8')
//        cursor = db.cursor()
//
//        # table 생성
//        sql = ''' CREATE TABLE ''' + coin_input + '''(
//        date_time DATETIME NOT NULL PRIMARY KEY,
//        opening_price INT(11) NOT NULL,
//        high_price INT(11) NOT NULL,
//        low_price INT(11) NOT NULL,
//        trade_price INT(11) NOT NULL
//        )
//        '''
//        cursor.execute(sql)
//
//        # 업비트 api reference
//        while True:
//        url = "https://api.upbit.com/v1/candles/minutes/240?market=KRW-" + \
//        coin_input + "&to=" + last_date_encoded + "&count=200"
//        headers = {"accept": "application/json"}
//        response = requests.get(url, headers=headers)
//        try:
//        response_json = response.json()
//        except requests.exceptions.JSONDecodeError as err:
//        pass
//        df = pd.DataFrame(response_json, columns=[
//        'candle_date_time_kst', 'opening_price', 'high_price', 'low_price', 'trade_price'])
//        result = pd.concat([result, df])
//        last_date = response_json[-1]['candle_date_time_kst']
//        last_date_encoded = parse.quote(last_date)
//        if last_date == "2017-09-26T17:00:00":
//        break
//
//        # csv파일 저장
//        result = result.drop_duplicates()
//        result.to_csv('Upbit.csv', index=False, encoding='cp949')
//
//        # db로 옮기기
//        cursor = db.cursor()
//        f = csv.reader(open('Upbit.csv'))
//        next(f)
//        table = '''INSERT INTO ''' + coin_input + ''' VALUES(%s, %s, %s, %s, %s)'''
//
//        for row in f:
//        cursor.execute(table, row)
//
//        db.commit()
//        cursor.close()
//
//        print("Imported")