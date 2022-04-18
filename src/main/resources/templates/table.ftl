<table class="table table-bordered table-hover table-condensed">
 <thead class="thead-dark">
    <tr>
      <th scope="col">物品名称</th>
      <th scope="col">最低售出价</th>
      <th scope="col">最高收购价</th>
      <th scope="col">售出-收购</th>
      <th scope="col">7天最低售出价</th>
      <th scope="col">售出-7天最低</th>
      <th scope="col">30天最低售出价</th>
      <th scope="col">售出-30天最低</th>
      <th scope="col">重点关注</th>
    </tr>
  </thead>
  <tbody>
  <#if tradeList?? && (tradeList?size > 0)>
      <#list tradeList as item>
        <tr
        <#if item.type == 1>
          class="success"
        <#elseif item.type == 2>
          class="warning"
        <#elseif item.type == 3>
          class="danger"
        </#if>
        >
            <td>${item.name}</td>
            <td>${item.minSellPrice}(${item.minSellVolumn})</td>
            <td>${item.maxBuyPrice}(${item.maxBuyVolumn})</td>
            <td>${item.buyToSellPrice}(${item.buyToSellPer})</td>
            <td>${item.minSell7Price}</td>
            <td>${item.sellTo7Price}(${item.sellTo7Per})</td>
            <td>${item.minSell30Price}</td>
            <td>${item.sellTo30Price}(${item.sellTo30Per})</td>
            <td>${item.point}</td>
        </tr>
      </#list>
     </#if>
  </tbody>
</thead>