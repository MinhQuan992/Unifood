function SendToPayment()
{
    //
}

function numberWithCommas(n) {
    return n;
}

function increaseValue()
{
    var value = parseInt(document.getElementById('number').value, 10);
    value = isNaN(value) ? 0 : value;
    value = value + 50;
    document.getElementById('number').value = value;
}

function reduceValue()
{
    var value = parseInt(document.getElementById('number').value, 10);
    value = isNaN(value) ? 0 : value;
    value = value - 50;
    document.getElementById('number').value = value;
}

function IncButton(Eid)
{
    var value = parseInt(document.getElementById(Eid).value, 10);
    if (document.getElementById('Check'+Eid).checked)
        if (value<99)
        {
            var price = parseInt(document.getElementById('SL'+Eid).value, 10);
            var totalcost = parseInt(document.getElementById('PD-main-menu-total-price').value, 10);
            totalcost += price;
            document.getElementById('PD-main-menu-total-price').value = numberWithCommas(totalcost);
        }
    value = isNaN(value) ? 0 : value;
    if (value<99) value++;
    document.getElementById(Eid).value = value;
}

function DecButton(Eid)
{
    var value = parseInt(document.getElementById(Eid).value, 10);
    if (document.getElementById("Check"+Eid).checked)
        if (value>1)
        {
            var price = parseInt(document.getElementById("SL"+Eid).value, 10);
            var totalcost = parseInt(document.getElementById('PD-main-menu-total-price').value, 10);
            totalcost -= price;
            document.getElementById('PD-main-menu-total-price').value = numberWithCommas(totalcost);
        }
    value = isNaN(value) ? 0 : value;
    if (value>1) value--;
    document.getElementById(Eid).value = value;
}

function Checked(Eid)
{
    if (document.getElementById('Check'+Eid).checked)
    {
        var totalcost = parseInt(document.getElementById('PD-main-menu-total-price').value, 10);
        var quantity = parseInt(document.getElementById(Eid).value, 10);
        var price = parseInt(document.getElementById('SL'+Eid).value, 10);
        totalcost += quantity*price;
        document.getElementById('PD-main-menu-total-price').value = numberWithCommas(totalcost);
    }
    else
    {
        var totalcost = parseInt(document.getElementById('PD-main-menu-total-price').value, 10);
        var quantity = parseInt(document.getElementById(Eid).value, 10);
        var price = parseInt(document.getElementById('SL'+Eid).value, 10);
        totalcost -= quantity*price;
        document.getElementById('PD-main-menu-total-price').value = numberWithCommas(totalcost);
    }
}


function OnCheck(Eid)
{
    if (document.getElementById('Check'+Eid).checked)
    {
        var totalcost = parseInt(document.getElementById('Total-Cost-Of-Cart').value, 10)+10;
        var totalnumber = parseInt(document.getElementById('Total-Number-Of-Item').value, 10);
        var quantity = parseInt(document.getElementById(Eid).value, 10);
        var price = parseInt(document.getElementById('SL'+Eid).value, 10);
        totalnumber += quantity;
        totalcost += quantity*price;
        document.getElementById('Total-Cost-Of-Cart').value = numberWithCommas(totalcost);
        document.getElementById('Total-Number-Of-Item').value = totalnumber;
        if (totalnumber!=0)
            document.getElementById('Shipping-fee').value = 'đ free';
        else
            document.getElementById('Shipping-fee').value = 'đ 0';
    }
    else
    {
        var totalcost = parseInt(document.getElementById('Total-Cost-Of-Cart').value, 10)+10;
        var totalnumber = parseInt(document.getElementById('Total-Number-Of-Item').value, 10);
        var quantity = parseInt(document.getElementById(Eid).value, 10);
        var price = parseInt(document.getElementById('SL'+Eid).value, 10);
        totalnumber += quantity;
        totalcost += quantity*price;
        document.getElementById('Total-Cost-Of-Cart').value = numberWithCommas(totalcost);
        document.getElementById('Total-Number-Of-Item').value = totalnumber;
        if (totalnumber!=0)
            document.getElementById('Shipping-fee').value = 'đ free';
        else
            document.getElementById('Shipping-fee').value = 'đ 0';
    }
}function SendToPayment()
{
    //
}

function numberWithCommas(n) {
    //var parts=n.toString().split(".");
    //return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (parts[1] ? "." + parts[1] : "") + " đ";
    return n;sss
}

function increaseValue()
{
    var value = parseInt(document.getElementById('number').value, 10);
    value = isNaN(value) ? 0 : value;
    value = value + 50;
    document.getElementById('number').value = value;
}

function reduceValue()
{
    var value = parseInt(document.getElementById('number').value, 10);
    value = isNaN(value) ? 0 : value;
    value = value - 50;
    document.getElementById('number').value = value;
}

function IncButton(Eid)
{
    var value = parseInt(document.getElementById(Eid).value, 10);
    if (document.getElementById('Check'+Eid).checked)
        if (value<99)
        {
            var price = parseInt(document.getElementById('SL'+Eid).value, 10);
            var totalcost = parseInt(document.getElementById('PD-main-menu-total-price').value, 10);
            totalcost += price;
            document.getElementById('PD-main-menu-total-price').value = numberWithCommas(totalcost);
        }
    value = isNaN(value) ? 0 : value;
    if (value<99) value++;
    document.getElementById(Eid).value = value;
}

function DecButton(Eid)
{
    var value = parseInt(document.getElementById(Eid).value, 10);
    if (document.getElementById("Check"+Eid).checked)
        if (value>1)
        {
            var price = parseInt(document.getElementById("SL"+Eid).value, 10);
            var totalcost = parseInt(document.getElementById('PD-main-menu-total-price').value, 10);
            totalcost -= price;
            document.getElementById('PD-main-menu-total-price').value = numberWithCommas(totalcost);
        }
    value = isNaN(value) ? 0 : value;
    if (value>1) value--;
    document.getElementById(Eid).value = value;
}

function Checked(Eid)
{
    if (document.getElementById('Check'+Eid).checked)
    {
        var totalcost = parseInt(document.getElementById('PD-main-menu-total-price').value, 10);
        var quantity = parseInt(document.getElementById(Eid).value, 10);
        var price = parseInt(document.getElementById('SL'+Eid).value, 10);
        totalcost += quantity*price;
        document.getElementById('PD-main-menu-total-price').value = numberWithCommas(totalcost);
    }
    else
    {
        var totalcost = parseInt(document.getElementById('PD-main-menu-total-price').value, 10);
        var quantity = parseInt(document.getElementById(Eid).value, 10);
        var price = parseInt(document.getElementById('SL'+Eid).value, 10);
        totalcost -= quantity*price;
        document.getElementById('PD-main-menu-total-price').value = numberWithCommas(totalcost);
    }
}


function OnCheck(Eid)
{
    if (document.getElementById('Check'+Eid).checked)
    {
        var totalcost = parseInt(document.getElementById('Total-Cost-Of-Cart').value, 10)+10;
        var totalnumber = parseInt(document.getElementById('Total-Number-Of-Item').value, 10);
        var quantity = parseInt(document.getElementById(Eid).value, 10);
        var price = parseInt(document.getElementById('SL'+Eid).value, 10);
        totalnumber += quantity;
        totalcost += quantity*price;
        document.getElementById('Total-Cost-Of-Cart').value = numberWithCommas(totalcost);
        document.getElementById('Total-Number-Of-Item').value = totalnumber;
        if (totalnumber!=0)
            document.getElementById('Shipping-fee').value = 'đ free';
        else
            document.getElementById('Shipping-fee').value = 'đ 0';
    }
    else
    {
        var totalcost = parseInt(document.getElementById('Total-Cost-Of-Cart').value, 10)+10;
        var totalnumber = parseInt(document.getElementById('Total-Number-Of-Item').value, 10);
        var quantity = parseInt(document.getElementById(Eid).value, 10);
        var price = parseInt(document.getElementById('SL'+Eid).value, 10);
        totalnumber += quantity;
        totalcost += quantity*price;
        document.getElementById('Total-Cost-Of-Cart').value = numberWithCommas(totalcost);
        document.getElementById('Total-Number-Of-Item').value = totalnumber;
        if (totalnumber!=0)
            document.getElementById('Shipping-fee').value = 'đ free';
        else
            document.getElementById('Shipping-fee').value = 'đ 0';
    }
}