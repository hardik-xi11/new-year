@import com.hardik.newyear.record.SpaceXLaunch

@param SpaceXLaunch launch

<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        .container { max-width: 600px; margin: 0 auto; font-family: sans-serif; border: 1px solid #ddd; border-radius: 8px; overflow: hidden; }
        .header { background-color: #1a1a1a; color: white; padding: 20px; text-align: center; }
        .content { padding: 20px; color: #333; }
        .badge { display: inline-block; padding: 4px 8px; border-radius: 4px; font-weight: bold; color: white; }
        .success { background-color: #28a745; }
        .fail { background-color: #d73a49; }
        .footer { background-color: #f6f8fa; padding: 15px; text-align: center; font-size: 12px; }
        .btn { display: inline-block; padding: 10px 20px; background-color: #0052cc; color: white; text-decoration: none; border-radius: 5px; margin-top: 15px; }
    </style>
    <title>Weekly SpaceX Mail</title>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>🚀 Latest SpaceX Launch</h1>
    </div>
    <div class="content">
        <h2>${launch.name()}</h2>
        <p><strong>Flight Number:</strong> ${launch.flightNumber()}</p>
        <p><strong>Date:</strong> ${launch.dateUtc()}</p>

        @if(launch.success())
            <span class="badge success">✅ SUCCESSFUL MISSION</span>
        @else
            <span class="badge fail">❌ MISSION FAILED</span>
        @endif

        <p style="margin-top: 15px;">${launch.details() != null ? launch.details() : "No details provided for this mission."}</p>

        @if(launch.links().webcast() != null)
            <a href="${launch.links().webcast()}" class="btn">Watch Webcast</a>
        @endif
    </div>
    <div class="footer">
        Sent via New Year Project • Rocket ID: ${launch.rocketId()}
    </div>
</div>
</body>
</html>