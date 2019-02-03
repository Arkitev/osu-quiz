from rest_framework import serializers

from tracks.models import Track


class TrackSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Track
        fields = '__all__'
