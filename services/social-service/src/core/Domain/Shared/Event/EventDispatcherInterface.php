<?php

namespace Core\Domain\Shared\Event;

interface EventDispatcherInterface
{
    public function dispatch(object $event): void;
}
