import dash
import dash_bootstrap_components as dbc

navbar = dbc.NavbarSimple(
    children=[
        dbc.NavItem(dbc.NavLink("2022-23 Ratings", href="/ratings")),
        dbc.NavItem(dbc.NavLink("Daily Projections", href="/projections")),
        dbc.NavItem(dbc.NavLink("Career Comparison", href="/compare")),
        dbc.NavItem(dbc.NavLink("About", href="google.com")),
    ],
    brand="NHL Darko",
    brand_href="/",
    color="#1B1B1B",
    dark=True,
)