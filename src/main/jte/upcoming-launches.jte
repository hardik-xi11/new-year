@import com.hardik.newyear.record.SpaceXLaunch
@import com.hardik.newyear.record.query.SpacexQueryResult
@import java.util.Map

@param SpacexQueryResult<SpaceXLaunch> results

<!DOCTYPE html>
<html>
<head>
    <style>
        body { font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }
        .wrapper { width: 100%; padding: 20px; }
        .container { max-width: 600px; margin: 0 auto; background: #ffffff; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 10px rgba(0,0,0,0.1); }
        .header { background-color: #000000; color: #ffffff; padding: 30px; text-align: center; }
        .mission-card { padding: 20px; border-bottom: 1px solid #eeeeee; }
        .patch { width: 60px; height: 60px; float: left; margin-right: 15px; }
        .mission-name { font-size: 20px; font-weight: bold; color: #1a1a1a; margin: 0; }
        .rocket-tag { display: inline-block; background: #0052cc; color: white; font-size: 11px; padding: 2px 8px; border-radius: 4px; text-transform: uppercase; margin-top: 5px; }
        .details { color: #666666; font-size: 14px; line-height: 1.5; margin: 10px 0; clear: both; }
        .btn { display: inline-block; padding: 10px 20px; background-color: #000000; color: #ffffff; text-decoration: none; border-radius: 6px; font-size: 14px; font-weight: bold; }
        .footer { padding: 20px; text-align: center; font-size: 12px; color: #999999; background: #f9f9f9; }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="container">
        <div class="header">
            <h1 style="margin:0;">🚀 Upcoming SpaceX Missions</h1>
            <p style="margin:5px 0 0 0; opacity: 0.8;">Next ${results.docs().size()} launches scheduled</p>
        </div>

        @for(SpaceXLaunch launch : results.docs())
            <div class="mission-card">
                @if(launch.links().patch().small() != null)
                    <img src="${launch.links().patch().small()}" class="patch" alt="patch">
                @else
                    <div class="patch" style="background: #eee; border-radius: 50%; text-align: center; line-height: 60px; font-size: 20px;">🚀</div>
                @endif

                <p class="mission-name">${launch.name()}</p>
                <span class="rocket-tag">${launch.getRocketDisplayName()}</span>

                <div class="details">
                    <strong>Date:</strong> ${launch.dateUtc().substring(0, 10)}<br>
                    <strong>Flight:</strong> #${launch.flightNumber()}<br>
                    ${launch.details() != null ? launch.details() : "Mission specifics will be announced closer to launch."}
                </div>

                @if(launch.links().webcast() != null)
                    <a href="${launch.links().webcast()}" class="btn">Set Reminder</a>
                @endif
            </div>
        @endfor

        <div class="footer">
            You are receiving this because you subscribed to New Year SpaceX Updates.<br>
            Total Upcoming Missions tracked: ${results.totalDocs()}
        </div>
    </div>
</div>
</body>
</html>