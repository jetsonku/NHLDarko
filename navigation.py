import dash
import dash_bootstrap_components as dbc

navbar = dbc.NavbarSimple(
    children=[
        dbc.Nav(
            children=[
                dbc.NavItem(dbc.NavLink("2022-23 Ratings", href="/ratings", active='exact')),
                dbc.NavItem(dbc.NavLink("Daily Projections", href="/projections", active='exact')),
                dbc.NavItem(dbc.NavLink("Career Comparison", href="/compare", active='exact')),
                dbc.NavItem(dbc.NavLink("About", href="google.com", active='exact')),
            ],
            pills=True, 


        )

    ],
    brand="NHL Darko",
    color="#0b1016",
    dark=True,
    style={
        "color": "#b48d22"
    }
)