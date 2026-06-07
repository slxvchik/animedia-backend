<?php

namespace Core\Domain\Shared\Eventable;

trait Eventable
{
    protected array $events = [];

    public function recordEvent(object $event): void
    {
        $this->events[] = $event;
    }

    /**
     * @return object[] events
     */
    public function releaseEvents(): array
    {
        $events = $this->events;
        $this->events = [];
        return $events;
    }
}
