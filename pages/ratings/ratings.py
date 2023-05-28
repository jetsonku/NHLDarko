import warnings
import pandas as pd
import dash
from dash import callback, html, dcc, dash_table
from dash.dependencies import Input, Output, State
import dash_bootstrap_components as dbc
import plotly.graph_objs as go

warnings.simplefilter(action='ignore', category=FutureWarning)
df = pd.read_csv('pages/ratings/data/ratings23.csv')
df['Raw'] = df['Raw'].round(2)
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
    title='NHL DARKO Ratings',
    name='Ratings',
    description='NHL DARKO Ratings',
)

teams = ['Anaheim Ducks','Arizona Coyotes','Boston Bruins','Buffalo Sabres','Calgary Flames','Carolina Hurricanes',
        'Colorado Avalanche','Columbus Blue Jackets','Dallas Stars','Detroit Red Wings','Edmonton Oilers','Florida Panthers',
        'Los Angeles Kings','Minnesota Wild','Montreal Canadiens','Nashville Predators','New Jersey Devils','New York Islanders',
        'New York Rangers','Ottawa Senators','Philadelphia Flyers','Pittsburgh Penguins','San Jose Sharks','Seattle Kraken',
        'St Louis Blues','Tampa Bay Lightning','Toronto Maple Leafs','Vancouver Canucks','Vegas Golden Knights','Washington Capitals', 
        'Winnipeg Jets']
pos = ['F', 'D', 'W', 'LW', 'RW', 'C']   

def discrete_background_color_bins(df, n_bins=20, columns='all'):
    import colorlover
    bounds = [i * (1.0 / n_bins) for i in range(n_bins+1)]
    if columns == 'all':
        if 'id' in df:
            df_numeric_columns = df.select_dtypes('number').drop(['id'], axis=1)
        else:
            df_numeric_columns = df.select_dtypes('number')
    else:
        df_numeric_columns = df[columns]
    df_max = 8
    df_min = -8
    ranges = [((df_max - df_min) * i) + df_min for i in bounds]
    styles = []
    for i in range(1, len(bounds)):
        min_bound = ranges[i - 1]
        max_bound = ranges[i]
        backgroundColor = ['rgb(13, 20, 84)', 'rgb(22, 26, 77)', 'rgb(139, 48, 161)', 'rgb(49, 44, 56)', 'rgb(50,50,50)', 'rgb(50,50,50)', 'rgb(68, 43, 46)', 'rgb(108, 31, 36)', 'rgb(128, 25, 31)', 'rgb(148, 19, 16)']

        scale = colorlover.interp(backgroundColor, 20)[i - 1]
        color = 'rgb(250, 250, 250)'
        for column in df_numeric_columns:
            styles.append({
                'if': {
                    'filter_query': (
                        '{{{column}}} >= {min_bound}' +
                        (' && {{{column}}} < {max_bound}' if (i < len(bounds) - 1) else '')
                    ).format(column=column, min_bound=min_bound, max_bound=max_bound),
                    'column_id': column
                },
                'textAlign': 'center',
                'backgroundColor': scale,
                'color': '#b48d22'
            })
            
    styles += ([{
                'if': {'filter_query': '{{{}}} is blank'.format(col), 'column_id': col},
                        'backgroundColor': 'rgb(250, 250, 250)',
                        'color': 'wrgb(250, 250, 250)',
                        'textAlign': 'center',
                    } for col in df_numeric_columns])

    return styles

styles = discrete_background_color_bins(df, columns=['Heat Index'])

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
        'font-family': 'Work Sans',
         'textAlign': 'center'
    },   
    css=[{"selector":"p", "rule":"margin: 0; text-align: left"}, {"selector":".dash-spreadsheet tr", "rule":"height: 40px;"}],
    page_action="native",
    page_current= 0,
    page_size= 20,
    style_cell_conditional=[
        {'if': {'column_id': 'Player'},
         'width': '18%',
         'textAlign': 'center'},
        {'if': {'column_id': 'Age'},
         'width': '6%',
         'textAlign': 'center'},
        {'if': {'column_id': 'Position'},
         'width': '6%',
         'textAlign': 'center'},
        {'if': {'column_id': 'Team'},
         'width': '7%',
         'textAlign': 'center'},
        {'if': {'column_id': 'Raw'},
         'width': '10%',
         'textAlign': 'center'},
        {'if': {'column_id': 'Overall'},
         'width': '8%',
         'textAlign': 'center'},
        {'if': {'column_id': 'Potential'},
         'width': '8%',
         'textAlign': 'center'},
        {'if': {'column_id': 'Short Term Overall'},
         'width': '8%',
         'textAlign': 'center'},
        {'if': {'column_id': 'Heat Index'},
         'width': '4%',
         'textAlign': 'center'}
    ],
    style_data_conditional=styles
    )



def layout():
    page = html.Div(
        [         
            html.Br(),
            dbc.Col(html.H1(['Player Ratings'], style={'font-family': 'Work Sans', 'font-weight': 'bold'}), width={"size": 10, "offset": 1}),
            html.Br(),
            dbc.Row([
                dbc.Col(html.Label(['Player Name:'], style={'font-weight': 'bold'}), width={"size": 2, "offset": 1}), 

                dbc.Col(html.Label(['Team:'], style={'font-weight': 'bold'}), width={"size": 2, "offset": 2}), 
                dbc.Col(html.Label(['Position:'], style={'font-weight': 'bold'}), width={"size": 2, "offset": 1})]),
            dbc.Row([   

                dbc.Col([dcc.Input(id="input_search", type='search', placeholder="Search", size=60)], align="center", width={"size": 3,"offset": 1}), 
                dbc.Col([dcc.Dropdown(teams, id='team_dropdown')], align="center", width={"size": 2, "offset": 1}), 
                dbc.Col([dcc.Dropdown(pos, id='pos_dropdown')], align="center", width={"size": 1, "offset": 1})]),
            html.Br(),
            dbc.Row([
                dbc.Col(html.Label(['Age:'], style={'font-weight': 'bold'}), width={"size": 10, "offset": 1})]), 
            dbc.Row([   
                dbc.Col([dcc.RangeSlider(18, 50, 1, value=[18, 50], id='age-slider')], align="center", width={"size": 10,"offset": 1})]),   
            html.Br(),      
            html.Br(),      
            dbc.Row(dbc.Col(datatable, width=10), justify="center",),
        ], style= {'width': '98%', 'display': 'inline-block'}
    )
    return page

@callback(
    Output('table', 'data'),
    Input("input_search", "value"),
    Input('team_dropdown', 'value'),
    Input('pos_dropdown', 'value'),
    Input('age-slider', 'value'),
)

def update_table(name, team, pos, ages):
    new_df = pd.read_csv('pages/ratings/data/ratings23.csv')
    new_df['Team'] = ['![myImage-1](assets/icons/teams/' + team.replace(' ', '_') + '.png)' for team in new_df['Team']]
    new_df['Raw'] = new_df['Raw'].round(2)

    if name is not None:
        new_df = new_df[(new_df.Player.str.lower()).str.contains(name.lower())]

    if team is not None:
        new_df = new_df[new_df.Team.str.contains(team.replace(' ', '_'))]

    if pos == 'F':
        new_df = new_df[(new_df.Position == 'L') | (new_df.Position == 'R') | (new_df.Position == 'C')]
    elif pos == 'W':
        new_df = new_df[(new_df.Position == 'L') | (new_df.Position == 'R') ]
    elif pos == 'D':
        new_df = new_df[new_df.Position == pos]
    elif pos is not None:
        new_df = new_df[new_df.Position == pos[0]]

    new_df = new_df[(new_df.Age > ages[0]) & (new_df.Age < ages[1])]
    
    return new_df.to_dict('records')


