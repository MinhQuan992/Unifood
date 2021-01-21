var submitForm = $('AjaxSendingForm')

function AcceptRespone(data)
{
    var ItemCode = $('AjaxSendingFormKeyValue').value
    QuantityUpdate(ItemCode)
}

function SendAjaxError(error)
{
    window.alert(error);
    window.alert("Ajax falied to send signal to servlet");
}

function AjaxSubmitFunction()
{
    $.ajax({
        type: submitForm.attr('method'),
        url: submitForm.attr('action'),
        data: submitForm.serialize(),
        success: AcceptRespone,
        error :SendAjaxError
    })
}

submitForm.submit(AjaxSubmitFunction)

function NoteButtonClick(Eid)
{
    var Value = document.getElementById("Take-Items-Note"+Eid).value;
    if (Value == "Note")
    {
        document.getElementById("Take-Items-Note"+Eid).value = 'OK';
        document.getElementById("Note"+Eid).disabled = false;
        document.getElementById("Note"+Eid).focus();
        return;
    }
    else
    {
        document.SendToPostRequest.ParaName.value = 'TakeNote';
        document.SendToPostRequest.KeyValue.value = Eid;
        var Note = document.getElementById("Note"+Eid).value;
        document.SendToPostRequest.Extend.value = Note;
        document.SendToPostRequest.submit();
    }
}

function ButtonClick(NamePara,Eid)
{
    document.SendToPostRequest.ParaName.value = NamePara;
    document.SendToPostRequest.KeyValue.value = Eid;
    document.SendToPostRequest.submit();
}

function OnDelete(NamePara,Eid)
{
    var confirn = window.confirm("Are you sure to delete it?");
    if (confirn)
    {
        document.SendToPostRequest.ParaName.value = NamePara;
        document.SendToPostRequest.KeyValue.value = Eid;
        document.SendToPostRequest.submit();
    }
}

function QuantityUpdate(Eid)
{
    var value = parseInt(document.getElementById(Eid).value, 10);
    if (document.getElementById('Check'+Eid).checked)
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
    if (value<99) value++;
    document.getElementById(Eid).value = value;
}

function IncButton(NamePara,Eid)
{
    var value = parseInt(document.getElementById(Eid).value, 10);
    if (document.getElementById('Check'+Eid).checked)

    value = isNaN(value) ? 0 : value;

    if (value<99)
    {
        document.SendToPostRequest.ParaName.value = NamePara;
        document.SendToPostRequest.KeyValue.value = Eid;
        $('AjaxSendingFormParaName').setAttribute("value",NamePara)
        $('AjaxSendingFormKeyValue').setAttribute("value",Eid)
        AjaxSubmitFunction()
        //document.SendToPostRequest.submit();
    }
    else
    {
        window.alert("Sorry!! We do not accept the quantity of item bigger 99, " +
            "if you want to have a big deal, please contact our customer care service!! Thanks");
    }


}


function DecButton(NamePara,Eid)
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
        document.SendToPostRequest.ParaName.value = NamePara;
        document.SendToPostRequest.KeyValue.value = Eid;
        document.SendToPostRequest.submit();
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

