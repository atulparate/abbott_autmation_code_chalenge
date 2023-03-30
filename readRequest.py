import requests

url = "https://datausa.io/api/data"
params = {"drilldowns": "Nation", "measures": "Population"}

response = requests.get(url, params=params).json()

data = response["data"]
source_name = response['source'][0]['annotations']['source_name']

populations_data = {}

# Capture year on year growth of the population
for row in data:
    year = row["Year"]
    population = row["Population"]
    populations_data[year] = population
    
start_year = list(populations_data.items())[0]
last_year = list(populations_data.items())[len(populations_data)-1]


## capture total number of years
total_years = int(start_year[0])-int(last_year[0])

population_growth = []
population_growth_dict = {}

## capture the population growth 
for i in range(len(populations_data)-1):
    current_year = int(list(populations_data.keys())[i])
    #print(current_year)
    prev_year = int(list(populations_data.keys())[i + 1])
    #print(prev_year)
    growth = (populations_data[str(current_year)] - populations_data[str(prev_year)]) / populations_data[str(prev_year)] * 100
    population_growth.append(growth)
    population_growth_dict[current_year] = growth


# capture max and min growth
max_growth = round(max(population_growth_dict.values()),2)
min_growth = round(min(population_growth_dict.values()),2)


peak_population_year = ''
lowest_population_year = ''

# capture the peak population year and lowest population year
for year,growth in population_growth_dict.items():
    if growth == max_growth:
        peak_population_year = year
    elif growth == min_growth:
        lowest_population_year = year

print (peak_population_year)
print(lowest_population_year)

print(f'According to {source_name}, in {total_years} years from {start_year[0]} to {last_year[0]}, peak population growth was {max_growth}% in {peak_population_year} and the lowest population increase was {min_growth}% in {lowest_population_year}.')
