import warnings
import pandas as pd
import dash
from dash import dash_table, callback
from dash.dependencies import Input, Output, State
import dash_bootstrap_components as dbc
import os

warnings.simplefilter(action="ignore", category=FutureWarning)
df = pd.read_csv('pages/ratings/data/ratings23.csv')
dash.register_page(
    __name__,
    path="/projections",
    title="Search financial database : okama",
    name="Projections",
    description="Okama financial database - free historical data for stocks, etf, mutual funds, indexes, currencies, commodities, rates etc.",
)


def layout():
    page = dbc.Container([
        dash_table.DataTable(df.to_dict('records'),
        [{"name": i, "id": i} for i in df.columns],
        sort_action="native",

        )
    ])
    return page