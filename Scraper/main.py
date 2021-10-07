import json

import requests
from bs4 import BeautifulSoup

soup = BeautifulSoup(requests.get("https://hotwheels.fandom.com/wiki/List_of_2021_Hot_Wheels").content,
                     "html.parser")

tables = soup.find_all("table", class_=["sortable", "wikitable" "jquery-tablesorter"], recursive=True)
table = tables[0]
cars = []
for tr in table.findAll('tr'):
    car = {
        "name": "",
        "series": "",
        "img": ""
    }
    tds = tr.findChildren('td')
    if len(tds) != 6:
        print(len(tds))
        continue

    car["name"] = tds[2].find('a').text
    series = tds[3]
    if series.find('font'):
        series = series.find('font')
    car["series"] = series.find('a').text
    car["img"] = tds[5].find('a').get('href')

    cars.append(car)
file = open("../cars.json","w+")
file.write(json.dumps(cars))
file.close()


