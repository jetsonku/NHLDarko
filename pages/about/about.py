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
                            html.P(["NHL DARKO is an adaptation of Kostya Medvedovsky's DARKO projections for the NBA. In this version, data is used from NaturalStatTrick to project a player's NHL Gamescore, a metric developed by Dom Luszczyszyn. Career games played may not be completely accurate due to missing data from some seasons. Only regular season games are considered."]), 
                            html.Br(),
                            html.P(["Data is obtained from NaturalStatTrick's amazing site, with regular season games going back to 2007-2008. From these boxscores, I've regenerated NHL Gamescores to get a dataset with every score for every player in every game since 2007-2008 (well almost). Using these scores, we create a model to project each player's Gamescore in their next game. To do this, DARKO uses two projections that are blended with a gradient boosted decision tree: a exponential decay model and a Kalman filter used on the scores. The final number from the decision tree is adjusted for things like venue, age, seasonality, and strength of opponent."]), 
                            html.Br(),
                            html.H2(["Plans for the Future"]), 
                            html.P(["There is more work to be done to make the model more stable and robust. One thing I would like to do is further optimize the adjustments to increase the importance of context to make the projections more consistent. There also needs to be more work done on career-long projection as the current Potential numbers are a crude estimate based on age and historical performance."]), 
                            html.P(["In terms of the website, I want to roll out a Daily Projections tab that will have projected performance of players playing on each day. This would theoretically help predict the outcome of games."]), 
                            html.Br(),

                            html.H2(["Links"]), 
                            dcc.Markdown('[DARKO](https://www.naturalstattrick.com/) by [Kostya Medvedovsky](https://twitter.com/kmedved)'),
                            dcc.Markdown('[NHL GameScore](https://www.naturalstattrick.com/) by [Dom Luszczyszyn](https://twitter.com/domluszczyszyn)'),
                            dcc.Markdown('[Age Curving in Sports](https://www.naturalstattrick.com/) by [Micah Blake McCurdy](https://twitter.com/IneffectiveMath)'),
                            dcc.Markdown('[Hockey Stat Cards](https://hockeystatcards.com/) by [Cole Palmer](https://twitter.com/cepvi0)'),
                            dcc.Markdown('[NaturalStatTrick](https://www.naturalstattrick.com/)'),
                            html.Br(),
                            html.H2(["About Me"]),
                            html.P(["My name is Jetson Ku, and I just graduated from the University of Maryland with a BS in Computer Science with a specialization in Data Science and a minor in Statistics. I've worked with NOAA, The National Weather Service, UMD Men's Basketball and UMD Football in analytical roles and I'm excited to apply my experience in a challenging data science role in the near future. I played club hockey during my time at Maryland in the ACHA D2."]), 
                            html.Br(),
                            html.H2(["Contact"]),
                            html.P(["If you're interested in working with me or have any ideas, suggestions, or criticism, please reach out!"]), 
                            dcc.Markdown('Twitter: [@jetsonku](https://twitter.com/JetsonKu)'),
                            dcc.Markdown('Email: [jetsonku@gmail.com](mailto:jetsonku@gmail.com)'),
                            dcc.Markdown('LinkedIn: [Jetson Ku](https://www.linkedin.com/in/jetson-ku-141b7b1a1/)'),

                            ], width={"size": 10, "offset": 1}, 
                            ),
                ]
            ),
        ], style= {'width': '98%', 'display': 'inline-block'}
    )
    return page



