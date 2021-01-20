var submitForm = $('#AjaxSendingForm');

function IncAcceptRespone(data)
{
    var ItemCode = $('#AjaxSendingFormKeyValue').attr("value");
    IncQuantityUpdate(ItemCode);
}

function DecAcceptRespone(data)
{
    var ItemCode = $('#AjaxSendingFormKeyValue').attr("value");
    DecQuantityUpdate(ItemCode);
}

function CheckAcceptRespone(data)
{
    var ItemCode = $('#AjaxSendingFormKeyValue').attr("value");
    OnCheck(ItemCode);
}

function DeleteAcceptRespone(data)
{
    var ItemCode = $('#AjaxSendingFormKeyValue').attr("value");
    OnDelete(ItemCode);
}

function SelectAllAcceptRespone(data)
{
    location.reload();
    return false;
}

function NoteAcceptRespone(data)
{
    var Eid = $('#AjaxSendingFormKeyValue').attr("value");
    document.getElementById("Take-Items-Note"+Eid).value = 'Note';
    document.getElementById("Note"+Eid).disabled = true;
    return;
}


function SendAjaxError(error)
{
    window.alert(error);
    window.alert("Ajax falied to send signal to servlet");
}

function AjaxSubmitFunction(succFunc, errFunc){
    console.log('this attribute was called!!');
    $.ajax({
        type: submitForm.attr('method'),
        url: 'AjaxAPI',
        data: submitForm.serialize(),
        success: succFunc,
        error: errFunc
    });
    return false;
}

function OnDelete(Eid)
{
    location.reload();
    return false;
}

function OnCheck(Eid)
{
    location.reload();
    return false;
}

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

        $('#AjaxSendingFormParaName').attr("value", 'TakeNote');
        $('#AjaxSendingFormKeyValue').attr("value", Eid);
        $('#AjaxSendingFormExtend').attr("value", Note);

        AjaxSubmitFunction(NoteAcceptRespone, SendAjaxError);
        //document.SendToPostRequest.submit();
    }
}

function ButtonClick(NamePara,Eid)
{
    document.SendToPostRequest.ParaName.value = NamePara;
    document.SendToPostRequest.KeyValue.value = Eid;
    $('#AjaxSendingFormParaName').attr("value", NamePara);
    $('#AjaxSendingFormKeyValue').attr("value", Eid);

    AjaxSubmitFunction(SelectAllAcceptRespone, SendAjaxError);

    //document.SendToPostRequest.submit();
}

function IncQuantityUpdate(Eid)
{
    console.log(Eid)
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
    value = isNaN(value) ? 0 : value;
    if (value<99)
    {
        document.SendToPostRequest.ParaName.value = NamePara;
        document.SendToPostRequest.KeyValue.value = Eid;
        $('#AjaxSendingFormParaName').attr("value", NamePara);
        $('#AjaxSendingFormKeyValue').attr("value", Eid);

        AjaxSubmitFunction(IncAcceptRespone, SendAjaxError);
        //QuantityUpdate(Eid);
        //document.SendToPostRequest.submit();
    }
    else
    {
        window.alert("Sorry!! We do not accept the quantity of item bigger 99, " +
            "if you want to have a big deal, please contact our customer care service!! Thanks");
    }
}

function DecQuantityUpdate(Eid)
{
    console.log(Eid)
    var value = parseInt(document.getElementById(Eid).value, 10);
    if (document.getElementById("Check"+Eid).checked)
        if (value>1)
        {
            var totalcost = parseInt(document.getElementById('Total-Cost-Of-Cart').value, 10);
            var totalnumber = parseInt(document.getElementById('Total-Number-Of-Item').value, 10);
            var price = parseInt(document.getElementById("SL"+Eid).value, 10);
            totalcost -= price;
            totalnumber -= 1;
            document.getElementById('Total-Cost-Of-Cart').value = totalcost;
            document.getElementById('Total-Number-Of-Item').value = totalnumber;
        }
    if (value>1)  value--;
    document.getElementById(Eid).value = value;
}

function DecButton(NamePara,Eid)
{
    var value = parseInt(document.getElementById(Eid).value, 10);
    value = isNaN(value) ? 1 : value;
    if (value>1) {
        //value--;
        document.SendToPostRequest.ParaName.value = NamePara;
        document.SendToPostRequest.KeyValue.value = Eid;
        $('#AjaxSendingFormParaName').attr("value", NamePara);
        $('#AjaxSendingFormKeyValue').attr("value", Eid);
        AjaxSubmitFunction(DecAcceptRespone, SendAjaxError);
        //document.SendToPostRequest.submit();
    }
    else
    {
        window.alert("The quantity can not be less than 1");
    }
    //document.getElementById(Eid).value = value;
}

function Checked(NamePara,Eid)
{
    document.SendToPostRequest.ParaName.value = NamePara;
    document.SendToPostRequest.KeyValue.value = Eid;
    $('#AjaxSendingFormParaName').attr("value", NamePara);
    $('#AjaxSendingFormKeyValue').attr("value", Eid);
    AjaxSubmitFunction(CheckAcceptRespone, SendAjaxError);
}

function Deleted(NamePara,Eid)
{
    var confirn = window.confirm("Are you sure to delete it?");
    if (confirn)
    {
        document.SendToPostRequest.ParaName.value = NamePara;
        document.SendToPostRequest.KeyValue.value = Eid;
        $('#AjaxSendingFormParaName').attr("value", NamePara);
        $('#AjaxSendingFormKeyValue').attr("value", Eid);
        AjaxSubmitFunction(DeleteAcceptRespone, SendAjaxError);
        //document.SendToPostRequest.submit();
    }
}

