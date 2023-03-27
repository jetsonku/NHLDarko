import warnings

import dash
from dash import html, dcc
import dash_bootstrap_components as dbc
from dash.dependencies import Input, Output, State

import plotly.io as pio

import navigation, footer

warnings.simplefilter(action="ignore", category=FutureWarning)

pio.templates.default = "simple_white"

app = dash.Dash(
    __name__,
    use_pages=True,
    update_title="Loading ...",
    external_stylesheets=[dbc.themes.BOOTSTRAP, dbc.icons.BOOTSTRAP],
    # suppress_callback_exceptions=True
)
server = app.server

app.layout = html.Div([dcc.Store(id="store"), navigation.navbar, dash.page_container, footer.footer()])



if __name__ == "__main__":
    app.run_server(debug=True, port=8050)