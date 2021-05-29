package com.project.bootcamp_santander.mapper;

import com.project.bootcamp_santander.model.Stock;
import com.project.bootcamp_santander.model.dto.StockDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {

    public Stock toEntity(StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setId(stockDTO.getId());
        stock.setName(stockDTO.getName());
        stock.setPrice(stockDTO.getPrice());
        stock.setVariation(stockDTO.getVariation());
        stock.setDate(stockDTO.getDate());

        return stock;
    }

    public StockDTO toDto(Stock stock) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setName(stock.getName());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setVariation(stock.getVariation());
        stockDTO.setDate(stock.getDate());

        return stockDTO;
    }

    public List<StockDTO> toDto(List<Stock> stockList) {
        return stockList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
