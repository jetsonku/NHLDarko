import plotly.graph_objects as go
import pandas as pd

def get_figure(players, time_type='Career Game'):
    fig = go.Figure()
    i = 0
    colors = ['rgba(204, 25, 25', 'rgba(25, 114, 204', 'rgba(25, 204, 114', ]
    for player in players:
        df_string = player.replace(" " , '_')
        df = pd.read_csv('projections/20222023/' + df_string + '.csv')
        fig.add_trace(go.Scatter(x=df[time_type], y=df['XGB'], mode='markers', opacity=0.075, marker=dict(color=colors[i] + ', 1)')))
        fig.add_trace(go.Scatter(x=df[time_type], y=df['XGB_Lowess'], name=player, mode='lines', marker=dict(color=colors[i] + ', 1)'))) # fill to trace0 y
        i += 1
    fig.update_layout(autosize=False, width=1100 , height=700)
        # Model fitting
    return fig