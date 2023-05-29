import warnings
import pandas as pd
import dash
from dash import callback, html, dcc
from dash.dependencies import Input, Output, State
import dash_bootstrap_components as dbc
import plotly.graph_objs as go
import sys

from pages.compare.src.get_figure import get_figure

warnings.simplefilter(action="ignore", category=FutureWarning)
fig = get_figure(['A.J. Greer'])

players = pd.read_csv('cleaned/master_skaters.csv')
players = players.columns
dash.register_page(
    __name__,
    path="/compare",
    title="NHL DARKO Comparison Tool",
    name="Comparison Tool",
    description="NHL DARKO Comparison Tool",
)
table_header = [
    html.Thead(html.Tr([html.Th("Rank"), html.Th("Name"), html.Th("Similarity Score")]))
]
sims = pd.read_csv('projections/game/similarities.csv')
sims = sims[sims.Player == 'A.J. Greer']
rows = []
for i in range(1, 11):
    try:
        rows.append(html.Tr([html.Td(i), html.Td(sims['Match_' + str(i)].to_list()[0]), html.Td(round(float(sims['Score_' + str(i)].to_list()[0]), 2))]))
    except:
        print('matches end at ', i)
table_body = [html.Tbody(rows)]

table = dbc.Table(
    # using the same table as in the above example
    table_header + table_body,
    bordered=True,
    dark=True,
    hover=True,
    responsive=True,
    id='similarity-table',
    striped=True,
    style={'color':'#b48d22', '--bs-table-striped-color':'#b48d22'}
)

def layout():
    page = html.Div(
        [

            html.Br(),
            dbc.Col(html.H1(['Comparison Tool'], style={'font-family': 'Work Sans', 'font-weight': 'bold'}), width={"size": 10, "offset": 1}),
            html.Br(),
            dbc.Row(
                [
                    dbc.Col([
                            html.Label(['Player Name:'], style={'font-weight': 'bold'}),
                            dcc.Dropdown(players, 'A.J. Greer', multi=False, id='primary_player', style={'font-weight': 'bold', 'color':'#b48d22'}),
                            html.Br(),
                            html.Label(['Players to Compare With: (Max 5)'], style={'font-weight': 'bold'}),
                            dcc.Dropdown(players, [], multi=True, id='other_players', style={'font-weight': 'bold', 'color':'#b48d22'}),
                            html.Br(),
                            html.Label(['Similar Players:'], style={'font-weight': 'bold'}),
                            html.Div(children=[dbc.Table(table)], id='table_container')], 
                            width=dict({'size':3, 'offset':1}), ),
                    dbc.Col([html.Div(dcc.Graph(figure=fig, id='graph'), style = {'display': 'inline-block', 'width': '100%','height': '100%'})], width=dict({'size':7}), align="center",),
                ]
            ),
        ], style= {'width': '98%', 'display': 'inline-block'}
    )
    return page

@callback(
    Output('graph', 'figure'),
    Output('table_container', 'children'),
    Input('primary_player', 'value'),
    Input('other_players', 'value')
)

def update_output(player1, others):
    sims = pd.read_csv('projections/game/similarities.csv')
    sims = sims[sims.Player == player1]
    rows = []
    for i in range(1, 11):
        try:
            rows.append(html.Tr([html.Td(i), html.Td(sims['Match_' + str(i)].to_list()[0]), html.Td(round(float(sims['Score_' + str(i)].to_list()[0]), 2))]))
        except:
            print(round(float(sims['Score_' + str(i)].to_list()[0]), 2))
    table_body = [html.Tbody(rows)]
    
    table = dbc.Table(
        # using the same table as in the above example
        table_header + table_body,
        bordered=True,
        dark=True,
        hover=True,
        responsive=True,
        id='similarity-table',
        striped=True,
        style={'color':'#b48d22', '--bs-table-striped-color':'#b48d22'}
    )
    return [get_figure([player1] + others), table]
