from django.db import models


class Track(models.Model):
    OSU = 'osu!'
    OSU_MANIA = 'osu!mania'
    GAME_MODES = (
        (OSU, 'osu'),
        (OSU_MANIA, 'osu!mania'),
    )
    artist = models.CharField(max_length=200, unique=True)
    game_mode = models.CharField(choices=GAME_MODES, default=OSU, max_length=9)
    title = models.CharField(max_length=200, unique=True)
    link = models.URLField()
