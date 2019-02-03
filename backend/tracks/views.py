from rest_framework import viewsets

from tracks.models import Track
from tracks.serializers import TrackSerializer


class TrackViewSet(viewsets.ModelViewSet):
    queryset = Track.objects.all()
    serializer_class = TrackSerializer
