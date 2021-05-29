package com.project.bootcamp_santander.service;

import com.project.bootcamp_santander.exceptions.BusinessExceptions;
import com.project.bootcamp_santander.exceptions.NotFoundException;
import com.project.bootcamp_santander.mapper.StockMapper;
import com.project.bootcamp_santander.model.Stock;
import com.project.bootcamp_santander.model.dto.StockDTO;
import com.project.bootcamp_santander.repository.StockRepository;
import com.project.bootcamp_santander.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.NotActiveException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @Transactional
    public StockDTO save(StockDTO stockDTO) {
        Optional<Stock> optionalStock = stockRepository.findByNameAndDate(stockDTO.getName(), stockDTO.getDate());
        if (optionalStock.isPresent()) {
            throw new BusinessExceptions(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = stockMapper.toEntity(stockDTO);
        stockRepository.save(stock);
        return stockMapper.toDto(stock);
    }

    @Transactional
    public StockDTO update(StockDTO stockDTO) {
        Optional<Stock> optionalStock = stockRepository.findByStockUpdate(stockDTO.getName(), stockDTO.getDate(), stockDTO.getId());
        if (optionalStock.isPresent()) {
            throw new BusinessExceptions(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = stockMapper.toEntity(stockDTO);
        stockRepository.save(stock);
        return stockMapper.toDto(stock);
    }

    @Transactional
    public StockDTO delete(Long id) {
        StockDTO stockDTO = this.findById(id);
        stockRepository.deleteById(stockDTO.getId());
        return stockDTO;
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        return stockMapper.toDto(stockRepository.findAll());
    }

    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        return stockRepository.findById(id).map(stockMapper::toDto).orElseThrow(NotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        return stockRepository.findByToday(LocalDate.now()).map(stockMapper::toDto).orElseThrow(NotFoundException::new);
    }
}
