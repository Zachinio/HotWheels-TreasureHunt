import json
import re

import requests
from bs4 import BeautifulSoup

soup = BeautifulSoup(requests.get("https://hotwheels.fandom.com/wiki/List_of_2019_Hot_Wheels").content,
                     "html.parser")

tables = soup.find_all("table", class_=["sortable", "wikitable" "jquery-tablesorter"], recursive=True)
table = tables[0]
cars = []
for tr in table.findAll('tr')[1:]:
    car = {
        "name": "",
        "series": "",
        "img": "",
        "isTreasure": False
    }
    tds = tr.findChildren('td')
    if len(tds) != 6:
        print(len(tds))
        continue
    car["name"] = tds[2].find('a', recursive=True).text
    series = tds[3]
    if series.find('font'):
        series = series.find('font')
    if series.find('a'):
        car["series"] = series.find('a').text
    if tds[3].find("b", recursive=True):
        if "Treasure Hunt" in tds[3].find("b", recursive=True).text:
            car["isTreasure"] = True

    if tds[5].find('a'):
        car["img"] = tds[5].find('a').get('href')
    else:
        print(print(tds[5]))

    cars.append(car)

print(cars)
file = open("../cars.json", "w+")
file.write(json.dumps(cars))
file.close()
