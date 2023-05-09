import warnings
import pandas as pd
import dash
from dash import callback, html, dcc, dash_table
from dash.dependencies import Input, Output, State
import dash_bootstrap_components as dbc
import plotly.graph_objs as go


dash.register_page(
    __name__,
    path='/about',
    title='About NHL DARKO',
    name='About',
    description='About NHL DARKO',
)

def layout():
    page = html.Div(
        [         
            html.Br(),
            dbc.Col(html.H1(['About'], style={'font-family': 'Work Sans', 'font-weight': 'bold'}), width={"size": 10, "offset": 1}),
            html.Br(),
            dbc.Row(
                [
                    dbc.Col([
                            html.P(["NHL DARKO is an adaptation of Kostya Medvedovsky's DARKO projections for the NBA. In this version, data is used from Natural Stat Trick to project a player's NHL Gamescore, a metric developed by Dom Luszczyszyn. More detail on implementation will be added at a later date. Career games played may not be completely accurate due to missing data from some seasons. Only regular season games are considered."]), 
                            html.Br(),
                            html.P(["- Jetson Ku"])], width={"size": 10, "offset": 1}, 
                            ),
                ]
            ),
        ], style= {'width': '98%', 'display': 'inline-block'}
    )
    return page



