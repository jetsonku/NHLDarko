import plotly.graph_objects as go
import pandas as pd

def get_figure(players, time_type='Career Game'):
    fig = go.Figure()
    i = 0
    colors = ['rgba(204, 0, 0', 'rgba(253, 127, 0', 'rgba(255, 255, 102', 'rgba(51, 255, 153', 'rgba(51, 200, 255', 'rgba(102, 102, 255']
    for player in players:
        df_string = player.replace(" " , '_')
        df = pd.read_csv('projections/game/all/' + df_string + '.csv')
        fig.add_trace(go.Scatter(x=df[time_type], y=df['XGB'], hovertemplate =
                                                                        f'<b>{player}</b><br>' +
                                                                        'Rating: %{y:.2f}<br>'+
                                                                        'Career Game: %{x}<extra></extra>',
                                                                    name=player, mode='markers', opacity=0.5, marker=dict(color=colors[i] + ', 1)')))
   #     fig.add_trace(go.Scatter(x=df[time_type], y=df['XGB_Lowess'], name=player, mode='lines', opacity=0.3, marker=dict(color=colors[i] + ', 1)'))) # fill to trace0 y
        i += 1
    fig.update_layout(paper_bgcolor="#2B3036", plot_bgcolor="#2B3036", font={'color':'#b48d22'}, hovermode="closest")
    fig.update_xaxes(title=time_type, linecolor='#b48d22')
    fig.update_yaxes(title='GameScore Rating', linecolor='#b48d22')
    fig.update_layout(    hoverlabel=dict(
        bgcolor="#2B3036",
        font_size=12,
        font_family="Work Sans"
    ))
        # Model fitting
    return fig