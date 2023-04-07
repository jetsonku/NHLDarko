import warnings
import pandas as pd
import dash
from dash import callback, html, dcc, dash_table
from dash.dependencies import Input, Output, State
import dash_bootstrap_components as dbc
import plotly.graph_objs as go

warnings.simplefilter(action='ignore', category=FutureWarning)
df = pd.read_csv('pages/ratings/data/ratings23.csv')
df['Team'] = ['![myImage-1](assets/icons/teams/' + team.replace(' ', '_') + '.png)' for team in df['Team']]
columns_format = []
for column in df.columns:
    if column == 'Team':
        columns_format.append(dict(id = column, name = column, presentation = 'markdown'))
    else:
        columns_format.append(dict(id = column, name = column))

dash.register_page(
    __name__,
    path='/ratings',
    title='Search financial database : okama',
    name='Ratings',
    description='Okama financial database - free historical data for stocks, etf, mutual funds, indexes, currencies, commodities, rates etc.',
)

teams = ['All', 'Anaheim Ducks','Arizona Coyotes','Boston Bruins','Buffalo Sabres','Calgary Flames','Carolina Hurricanes',
        'Colorado Avalanche','Columbus Blue Jackets','Dallas Stars','Detroit Red Wings','Edmonton Oilers','Florida Panthers',
        'Los Angeles Kings','Minnesota Wild','Montreal Canadiens','Nashville Predators','New Jersey Devils','New York Islanders',
        'New York Rangers','Ottawa Senators','Philadelphia Flyers','Pittsburgh Penguins','San Jose Sharks','Seattle Kraken',
        'St Louis Blues','Tampa Bay Lightning','Toronto Maple Leafs','Vancouver Canucks','Vegas Golden Knights','Washington Capitals', 
        'Winnipeg Jets']
pos = ['All', 'F', 'D', 'LW', 'RW', 'C']   

datatable = dash_table.DataTable(
    df.to_dict('records'), 
    sort_action='native', 
    id='table', 
    markdown_options= {'html':True}, 
    columns = columns_format, 
    style_header={
        'backgroundColor': 'rgb(30, 30, 30)',
    },
    style_data={
        'backgroundColor': 'rgb(50, 50, 50)',
    },   
    css=[dict(selector= "p", rule= "margin: 0; text-align: center")]
    )



def layout():
    page = html.Div(
        [

            dbc.Col(
                [
                    dbc.Row([dcc.Dropdown(teams, 'All', id='team_dropdown')], align="center"),
                    dbc.Row([dcc.Dropdown(pos, 'All', id='pos_dropdown')], align="center"),

                    dbc.Row(datatable),
                ]
            ),
        ]
    )
    return page

@callback(
    Output('table', 'data'),
    Input('team_dropdown', 'value'),
    Input('pos_dropdown', 'value'),
)

def update_table(team, pos):
    new_df = pd.read_csv('pages/ratings/data/ratings23.csv')
    new_df['Team'] = ['![myImage-1](assets/icons/teams/' + team.replace(' ', '_') + '.png)' for team in new_df['Team']]

    if team != 'All':
        new_df = new_df[new_df.Team.str.contains(team.replace(' ', '_'))]
    
    if pos == 'F':
        new_df = new_df[(new_df.Position == 'L') | (new_df.Position == 'R') | (new_df.Position == 'C')]
    elif pos != 'All':
        new_df = new_df[new_df.Position == pos]
    
    return new_df.to_dict('records')


