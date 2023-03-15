import requests

url = "https://datausa.io/api/data"
params = {"drilldowns": "Nation", "measures": "Population"}

response = requests.get(url, params=params).json()

data = response["data"]
source = response['source'][0]['annotations']['source_name']

print(source)
start_year = (int)(data[-1]["Year"])
end_year =(int)(data[0]["Year"]) 
max_growth = 0
min_growth = float("inf")
max_growth_year = ""
min_growth_year = ""


for row in data:
    growth = row["Population"]
    if growth > max_growth:
        max_growth = growth
        max_growth_year = row["Year"]
    if growth < min_growth:
        min_growth = growth
        min_growth_year = row["Year"]

years = end_year - start_year



peak_growth = round((max_growth - data[1]["Population"]) / data[0]["Population"] * 100, 2)
lowest_growth = round((min_growth - data[1]["Population"]) / data[0]["Population"] * 100, 2)

print(f"According to {source}, in {years} years from {start_year} to {end_year}, peak population growth was {peak_growth}% in {max_growth_year} and the lowest population increase was {lowest_growth}% in {min_growth_year}.")
