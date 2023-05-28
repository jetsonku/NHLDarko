import dash
from dash import html, dcc


def footer():
    return html.Footer(
        html.Div(
            [
                html.Hr(),
                dcc.Markdown(
                    """
                Data courtesy of [NaturalStatTrick](https://www.naturalstattrick.com/).
                """
                )
            ],
            style={"text-align": "center"},
            className="p-2",
        ),
    )