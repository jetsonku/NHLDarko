import warnings
import pandas as pd
import dash
from dash import callback, html, dcc
from dash.dependencies import Input, Output, State
import dash_bootstrap_components as dbc
import plotly.graph_objs as go

from pages.compare.src.get_figure import get_figure

warnings.simplefilter(action="ignore", category=FutureWarning)
fig = get_figure(['A.J. Greer'])
players = pd.read_csv('pages/ratings/data/ratings23.csv')['Player'].sort_values()
dash.register_page(
    __name__,
    path="/compare",
    title="Search financial database : okama",
    name="Comparison Tool",
    description="Okama financial database - free historical data for stocks, etf, mutual funds, indexes, currencies, commodities, rates etc.",
)


def layout():
    page = html.Div(
        [

            dbc.Row(
                [
                    dbc.Col([dcc.Dropdown(players, 'A.J. Greer', multi=True, id='dropdown'),
                            html.Div()], align="center", width=dict({'size':2, 'offset':1})),
                    dbc.Col(dcc.Graph(figure=fig, id='graph'), width="auto"),
                ]
            ),
        ]
    )
    return page

@callback(
    Output('graph', 'figure'),
    Input('dropdown', 'value')
)
def update_output(value):
    return get_figure(value)