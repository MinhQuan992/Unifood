function IncButton(Eid,Url)
{
    var value = parseInt(document.getElementById(Eid).value, 10);
    /*if (document.getElementById('Check'+Eid).checked)
        if (value<99)
        {
            var totalcost = parseInt(document.getElementById('Total-Cost-Of-Cart').value, 10);
            var totalnumber = parseInt(document.getElementById('Total-Number-Of-Item').value, 10);
            var price = parseInt(document.getElementById("SL"+Eid).value, 10);
            totalcost += price;
            totalnumber += 1;
            document.getElementById('Total-Cost-Of-Cart').value = totalcost;
            document.getElementById('Total-Number-Of-Item').value = totalnumber;
        }

    value = isNaN(value) ? 0 : value;*/
    if (value<99)
    {
        //value++;
        document.location.href = Url;
    }
    else
    {
        window.alert("Sorry!! We do not accept the quantity of item bigger 99, " +
            "if you want to have a big deal, please contact our customer care service!! Thanks");
    }
    //document.getElementById(Eid).value = value;
}

function DecButton(Eid,Url)
{
    var value = parseInt(document.getElementById(Eid).value, 10);
    /*if (document.getElementById("Check"+Eid).checked)
        if (value>1)
        {
            var totalcost = parseInt(document.getElementById('Total-Cost-Of-Cart').value, 10);
            var totalnumber = parseInt(document.getElementById('Total-Number-Of-Item').value, 10);
            var price = parseInt(document.getElementById("SL"+Eid).value, 10);
            totalcost -= price;
            totalnumber -= 1;
            document.getElementById('Total-Cost-Of-Cart').value = totalcost;
            document.getElementById('Total-Number-Of-Item').value = totalnumber;
        }*/
    value = isNaN(value) ? 1 : value;
    if (value>1) {
        //value--;
        document.location.href = Url;
    }
    else
    {
        window.alert("The quantity can not be less than 1");
    }
    //document.getElementById(Eid).value = value;
}

function OnCheck(Eid, Url)
{
    /*if (document.getElementById('Check'+Eid).checked)
    {
        var totalcost = parseInt(document.getElementById('Total-Cost-Of-Cart').value, 10);
        var totalnumber = parseInt(document.getElementById('Total-Number-Of-Item').value, 10);
        var quantity = parseInt(document.getElementById(Eid).value, 10);
        var price = parseInt(document.getElementById('SL'+Eid).value, 10);
        totalnumber += quantity;
        totalcost += quantity*price;
        document.getElementById('Total-Cost-Of-Cart').value = totalcost;
        document.getElementById('Total-Number-Of-Item').value = totalnumber;
        if (totalnumber!=0)
            document.getElementById('Shipping-fee').value = '19,000';
        else
            document.getElementById('Shipping-fee').value = '0';
    }
    else
    {
        var totalcost = parseInt(document.getElementById('Total-Cost-Of-Cart').value, 10);
        var totalnumber = parseInt(document.getElementById('Total-Number-Of-Item').value, 10);
        var quantity = parseInt(document.getElementById(Eid).value, 10);
        var price = parseInt(document.getElementById('SL'+Eid).value, 10);
        totalnumber -= quantity;
        totalcost -= quantity*price;
        document.getElementById('Total-Cost-Of-Cart').value = totalcost;
        document.getElementById('Total-Number-Of-Item').value = totalnumber;
        if (totalnumber!=0)
            document.getElementById('Shipping-fee').value = '19,000';
        else
            document.getElementById('Shipping-fee').value = '0';
    }*/
    document.location.href = Url;
}

function OnDelete(Url)
{
    var confirn = window.confirm("Are you sure to delete it?");
    if (confirn)
    {
        document.location.href = Url;
    }
}

function OnSelectAll(Url)
{
    document.location.href = Url;
}